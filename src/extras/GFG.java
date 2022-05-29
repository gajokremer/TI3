package extras;

import java.util.*;

public class GFG {

    public static class Node implements Comparator<Node> {

        private int node;
        private int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        public int getNode() {
            return node;
        }

        public void setNode(int node) {
            this.node = node;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        @Override
        public int compare(Node o1, Node o2) {

            return Integer.compare(o1.getCost(), o2.getCost());
        }

        @Override
        public String toString() {
            return "{node=" + node +
                    ", cost=" + cost +
                    '}';
        }
    }

    private int[] dist;
    private Set<Integer> settled;
    private PriorityQueue<Node> pq;
    private int v;
    private List<List<Node>> adj;

    public GFG(int v) {
        this.v = v;
        dist = new int[v];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(v, new Node());
    }

    public int[] getDist() {
        return dist;
    }

    public void setDist(int[] dist) {
        this.dist = dist;
    }

    public Set<Integer> getSettled() {
        return settled;
    }

    public void setSettled(Set<Integer> settled) {
        this.settled = settled;
    }

    public PriorityQueue<Node> getPq() {
        return pq;
    }

    public void setPq(PriorityQueue<Node> pq) {
        this.pq = pq;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public List<List<Node>> getAdj() {
        return adj;
    }

    public void setAdj(List<List<Node>> adj) {
        this.adj = adj;
    }

    public void dijkstra(List<List<Node>> adj, int src) {

        this.adj = adj;

        for (int i = 0; i < v; i++) {

            dist[i] = Integer.MAX_VALUE;
        }

        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (settled.size() != v) {

            if (pq.isEmpty()) {

                return;
            }

            int u = pq.remove().getNode();

            if (settled.contains(u)) {

                continue;
            }

            settled.add(u);

            eNeighbours(u);
        }
    }

    private void eNeighbours(int u) {

        int edgeDistance = -1;
        int newDistance = -1;

        for (int i = 0; i < adj.get(u).size(); i++) {

            Node v = adj.get(u).get(i);

            if (!settled.contains(v.getNode())) {

                edgeDistance = v.getCost();
                newDistance = dist[u] + edgeDistance;

                if (newDistance < dist[v.getNode()]) {

                    dist[v.getNode()] = newDistance;
                }

                pq.add(new Node(v.getNode(), dist[v.getNode()]));
            }
        }
    }

    public static void main(String[] args) {

        int v = 5;
        int source = 0;

        List<List<Node>> adj = new ArrayList<>();

        for (int i = 0; i < v; i++) {

            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

//        System.out.println(adj);

        adj.get(0).add(new Node(1, 9));
        adj.get(0).add(new Node(2, 6));
        adj.get(0).add(new Node(3, 5));
        adj.get(0).add(new Node(4, 3));

//        System.out.println(adj);

        adj.get(2).add(new Node(1, 2));
        adj.get(2).add(new Node(3, 4));

//        System.out.println(adj);

        GFG dpq = new GFG(v);
        dpq.dijkstra(adj, source);

        System.out.println("\nThe shorted path from node :");

        for (int i = 0; i < dpq.getDist().length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.getDist()[i]);
    }
}