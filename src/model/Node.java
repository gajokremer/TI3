package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Node<T> implements Cloneable {

    private T value;
//    private Integer distance = Integer.MAX_VALUE;
    private Double distance = Double.POSITIVE_INFINITY;
    private List<Node<T>> shortestPath = new LinkedList<>();
    private final Map<Node<T>, Double> adjacentNodes = new HashMap<>();

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public void addDestination(Node<T> destination, double distance) {

        adjacentNodes.put(destination, distance);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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
        return "Node{" +
                "value=" + value +
                ", distance=" + distance +
//                ", shortestPath=" + shortestPath +
//                ", adjacentNodes=" + adjacentNodes +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}