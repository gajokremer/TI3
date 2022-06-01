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
}