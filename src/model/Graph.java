package model;

import java.util.*;

public class Graph<T> {

    private int n;
    private List<Node<T>> nodes;

    public Graph(int n) {
        this.n = n;
        nodes = new ArrayList<>(n);
    }

    public boolean addNode(Node<T> node) {

        if (!nodes.contains(node) && nodes.size() < n) {

            nodes.add(node);
            return true;
        }

        return false;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node<T>> nodes) {
        this.nodes = nodes;
    }

//    public Node<T> getSpecificNode(List<Node<T>> nodeList, T data) {
    public Node<T> getSpecificNode(T data) {

        for (Node<T> node : nodes) {

            if (node.getData().equals(data)) {

                return node;
            }
        }

        return null;
    }

    public Node<T> getNodeFromList(List<Node<T>> nodeList, T data) {

        for (Node<T> node : nodeList) {

            if (node.getData().equals(data)) {

                return node;
            }
        }

        return null;
    }

    public void resetNodes() {

        for (Node<T> node : nodes) {

            node.setDistance(Double.POSITIVE_INFINITY);
            node.setVisited(false);
        }
    }

    @Override
    public String toString() {
        return "DijkstraGraph{" +
                "n=" + n +
                ", nodes=" + nodes +
                '}';
    }
}