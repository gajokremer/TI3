package model;

import java.util.*;

public class DPQ<T> {

    public static class Vertex<T> implements Comparator<Vertex<T>> {

        private T value;
        private int index;
        private int cost;

        public Vertex(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }

        public Vertex() {

        }

//        public Vertex(T value) {
//            this.value = value;
//        }
//
//        public Vertex(T value, int index, int cost) {
//            this.value = value;
//            this.index = index;
//            this.cost = cost;
//        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return  "{index" + index +
                    ", cost=" + cost +
                    '}';
        }

        @Override
        public int compare(Vertex<T> o1, Vertex<T> o2) {
            return Integer.compare(o1.getCost(), o2.getCost());
        }
    }

    private int v;
    private int[] dist;
    private List<List<Vertex<T>>> adj;
    private Set<Integer> settled;
    private PriorityQueue<Vertex<T>> unsettled;

    public DPQ(int v) {
        this.v = v;
        dist = new int[v];
        settled = new HashSet<>();
        unsettled = new PriorityQueue<>(v, new Vertex<>());
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public int[] getDist() {
        return dist;
    }

    public void setDist(int[] dist) {
        this.dist = dist;
    }

    public List<List<Vertex<T>>> getAdj() {
        return adj;
    }

    public void setAdj(List<List<Vertex<T>>> adj) {
        this.adj = adj;
    }

    public Set<Integer> getSettled() {
        return settled;
    }

    public void setSettled(Set<Integer> settled) {
        this.settled = settled;
    }

    public PriorityQueue<Vertex<T>> getUnsettled() {
        return unsettled;
    }

    public void setUnsettled(PriorityQueue<Vertex<T>> unsettled) {
        this.unsettled = unsettled;
    }

    public void dijkstra(List<List<Vertex<T>>> adj, int src) {

        this.adj = adj;

        for (int i = 0; i < v; i++) {

            dist[i] = Integer.MAX_VALUE;
        }

//        PriorityQueue<Vertex<T>> unsettled = new PriorityQueue<>();

//        Set<Integer> settled = new HashSet<>();

        unsettled.add(new Vertex<T>(src, 0));
        dist[src] = 0;

        while (settled.size() != v) {

            if (unsettled.isEmpty()) {

                return;
            }

            int u = unsettled.remove().getIndex();

            if (settled.contains(u)) {

                continue;
            }

            settled.add(u);

            edgeNeighbours(u);
        }
    }

    private void edgeNeighbours(int u) {

        int edgeDistance;
        int newDistance;

        for (int i = 0; i < adj.get(u).size(); i++) {

            Vertex<T> v = adj.get(u).get(i);

            if (!settled.contains(v.getIndex())) {

                edgeDistance = v.getCost();
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.getIndex()]) {

                    dist[v.getIndex()] = newDistance;
                }

                unsettled.add(new Vertex<T>(v.getIndex(), dist[v.getIndex()]));
            }
        }
    }

    private int findShortestPath(int src, int dest) {

        return dist[dest];
    }

    public static void main(String[] args) {

        int n = 5;
        int src = 0;
        int dest = 2;

        List<List<Vertex<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            List<Vertex<Integer>> item = new ArrayList<>();
            adj.add(item);
        }

        adj.get(0).add(new Vertex<>(1, 3));
        adj.get(0).add(new Vertex<>(3, 7));
        adj.get(0).add(new Vertex<>(4, 8));

        adj.get(1).add(new Vertex<>(2, 1));
        adj.get(1).add(new Vertex<>(3, 4));

        adj.get(3).add(new Vertex<>(2, 2));

        adj.get(4).add(new Vertex<>(3, 3));


        DPQ<Integer> dpq = new DPQ<>(n);

        dpq.dijkstra(adj, 0);

        System.out.println(adj);

        int shortest = dpq.findShortestPath(src, dest);

        System.out.println("The shortest distance from " + src
                + " to " + dest + " is: " + shortest);
    }
}