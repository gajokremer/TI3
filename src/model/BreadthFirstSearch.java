package model;

import java.util.*;

public class BreadthFirstSearch<T> {

    private final Node<T> startNode;
    private List<Node<T>> visitedNodes;

    public BreadthFirstSearch(Node<T> startNode) {
        this.startNode = startNode;
        visitedNodes = new ArrayList<>();
    }

    public Node<T> getStartNode() {
        return startNode;
    }

    public List<Node<T>> getVisitedNodes() {
        return visitedNodes;
    }

    public void setVisitedNodes(List<Node<T>> visitedNodes) {
        this.visitedNodes = visitedNodes;
    }

    public void traverse() {

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {

            Node<T> current = queue.poll();

            if (!current.isVisited()) {

                current.setVisited(true);
                visitedNodes.add(current);
                queue.addAll(current.getAdjacentNodes().keySet());
            }
        }
    }

    public static void main(String[] args) {

        Node<Integer> v0 = new Node<>(0);
        Node<Integer> v1 = new Node<>(1);
        Node<Integer> v2 = new Node<>(2);
        Node<Integer> v3 = new Node<>(3);
        Node<Integer> v4 = new Node<>(4);
        Node<Integer> v5 = new Node<>(5);
        Node<Integer> v6 = new Node<>(6);

//        v0.setNeighbours(Arrays.asList(v1, v5, v6));
//        v1.setNeighbours(Arrays.asList(v3, v4, v5));
//        v4.setNeighbours(Arrays.asList(v2, v6));
//        v6.setNeighbours(Collections.singletonList(v0));

        v0.addDestination(v1, Double.POSITIVE_INFINITY);
        v0.addDestination(v5, Double.POSITIVE_INFINITY);
        v0.addDestination(v6, Double.POSITIVE_INFINITY);

        v1.addDestination(v3, Double.POSITIVE_INFINITY);
        v1.addDestination(v4, Double.POSITIVE_INFINITY);
        v1.addDestination(v5, Double.POSITIVE_INFINITY);

        v4.addDestination(v2, Double.POSITIVE_INFINITY);
        v4.addDestination(v6, Double.POSITIVE_INFINITY);

        v6.addDestination(v0, Double.POSITIVE_INFINITY);

        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<>(v1);
        bfs.traverse();
    }
}