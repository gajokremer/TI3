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

    @Override
    public String toString() {
        return "Graph{" +
                "n=" + n +
                ", nodes=" + nodes +
                '}';
    }
}