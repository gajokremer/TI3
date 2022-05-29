package model;

import java.util.*;

public class Graph<T> {

    private List<Node<T>> nodes = new ArrayList<>();

    public void addNode(Node<T> node) {

        if (!nodes.contains(node)) {

            nodes.add(node);
        }
    }

    public List<Node<T>> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node<T>> nodes) {
        this.nodes = nodes;
    }
}