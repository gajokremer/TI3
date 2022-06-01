package model;

import java.util.*;

public class Dijkstra<T> {

//    public Graph<T> calculateShortestPathFromSource(Graph<T> graph, Node<T> source) {
    public void calculateShortestPathFromSource(Graph<T> graph, Node<T> source) {

        source.setDistance(0.0);

//        Set<GenericNode<T>> settledNodes = new HashSet<>();
//        Set<Graph.Node> unsettledNodes = new HashSet<>();
        List<Node<T>> settledNodes = new ArrayList<>();
        List<Node<T>> unsettledNodes = new ArrayList<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {

            Node<T> currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

//            for (Map.Entry<Node, Integer> adjacencyPair :
            for (Map.Entry<Node<T>, Double> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {

                Node<T> adjacentNode = adjacencyPair.getKey();
//                Integer edgeWeight = adjacencyPair.getValue();
                double edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {

                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }

//        return graph;
    }

    private void calculateMinimumDistance(Node<T> evaluationNode,
                                          Double edgeWeight, Node<T> sourceNode) {

        Double sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {

            evaluationNode.setDistance(sourceDistance + edgeWeight);

            LinkedList<Node<T>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

//    private Graph.Node getLowestDistanceNode(Set<Graph.Node> unsettledNodes) {
    private Node<T> getLowestDistanceNode(List<Node<T>> unsettledNodes) {

        Node<T> lowestDistanceNode = null;
//        int lowestDistance  = Integer.MAX_VALUE;
        double lowestDistance = Double.POSITIVE_INFINITY;

        for (Node<T> node : unsettledNodes) {

            double nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {

                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    public String printPath(Node<T> source, Node<T> destination) {

        return "From " + source.getData() + " to " +
                        destination.getData() + ": " + destination.getDistance();
    }
}