package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node<T> {

    private final T data;
    private Double distance = Double.POSITIVE_INFINITY;
    private boolean visited;
    private List<Node<T>> shortestPath;
    private final Map<Node<T>, Double> adjacentNodes = new HashMap<>();

    public Node(T value) {
        this.data = value;
        visited = false;
        shortestPath = new LinkedList<>();
    }

    public void addDestination(Node<T> destination, double distance) {

        adjacentNodes.put(destination, distance);
    }

    public T getData() {
        return data;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Node<T>> getShortestPath() {
        return shortestPath;
    }

    public void setShortestPath(List<Node<T>> shortestPath) {
        this.shortestPath = shortestPath;
    }

    public Map<Node<T>, Double> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public String toString() {
        return "{data=" + data +
//                ", distance=" + distance +
                ", visited=" + visited +
                '}';
    }
}