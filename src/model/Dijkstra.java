package model;

import java.util.*;

public class Dijkstra<T> {

    public Graph<T> calculateShortestPathFromSource(Graph<T> graph, Node<T> source) {

        source.setDistance(0.0);

//        Set<GenericNode<T>> settledNodes = new HashSet<>();
//        Set<Graph.Node> unsettledNodes = new HashSet<>();
        ArrayList<Node<T>> settledNodes = new ArrayList<>();
        ArrayList<Node<T>> unsettledNodes = new ArrayList<>();

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

        return graph;
    }

    private void calculateMinimumDistance(Node<T> evaluationNode, Double edgeWeight, Node<T> sourceNode) {

        Double sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {

            evaluationNode.setDistance(sourceDistance + edgeWeight);

            LinkedList<Node<T>> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

//    private Graph.Node getLowestDistanceNode(Set<Graph.Node> unsettledNodes) {
    private Node<T> getLowestDistanceNode(ArrayList<Node<T>> unsettledNodes) {

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

//    public String showPath(Graph<T> graph, Node<T> source, Node<T> destination) {
    public String showPath(Node<T> source, Node<T> destination) {

        return "From " + source.getValue() + " to " +
                        destination.getValue() + ": " + destination.getDistance();

//        String line = "From " + source.getValue() + " to " +
//                        destination.getValue() + ": ";
//
//            if (destination.getDistance() == Integer.MAX_VALUE) {
//
//                line += Double.POSITIVE_INFINITY;
//
//            } else {
//
//                line += destination.getDistance();
//            }
//
//            return line;
    }

//    public static void main(String[] args) {
//
////        GenericNode<Integer> node0 = new GenericNode<>(0);
////        GenericNode<Integer> node1 = new GenericNode<>(1);
////        GenericNode<Integer> node2 = new GenericNode<>(2);
////        GenericNode<Integer> node3 = new GenericNode<>(3);
////        GenericNode<Integer> node4 = new GenericNode<>(4);
////
////        node0.addDestination(node1, 3);
////        node0.addDestination(node3, 7);
////        node0.addDestination(node4, 8);
////
////        node1.addDestination(node2, 1);
////        node1.addDestination(node3, 4);
////
////        node3.addDestination(node2, 2);
////
////        node4.addDestination(node3, 3);
//////
////        GenericGraph<Integer> graph = new GenericGraph<>();
////
////        graph.addNode(node0);
////        graph.addNode(node1);
////        graph.addNode(node2);
////        graph.addNode(node3);
////        graph.addNode(node4);
//
////        GenericDijkstra<Integer> dijkstra = new GenericDijkstra<>();
////
////        graph = dijkstra.calculateShortestPathFromSource(graph, node0);
////
////        for (int i = 0; i < graph.getNodes().size(); i++) {
////
////            System.out.println("From " + node0.getIndex() + " to " + i + ": " + graph.getNodes().get(i).getDistance());
////        }
//
//        Node<Character> nodeA = new Node<>('A');
//        Node<Character> nodeB = new Node<>('B');
//        Node<Character> nodeC = new Node<>('C');
//        Node<Character> nodeD = new Node<>('D');
//        Node<Character> nodeE = new Node<>('E');
//
//        nodeA.addDestination(nodeC, 12);
//        nodeA.addDestination(nodeD, 60);
//
//        nodeB.addDestination(nodeA, 10);
//
//        nodeC.addDestination(nodeB, 20);
//        nodeC.addDestination(nodeD, 32);
//
//        nodeE.addDestination(nodeA, 7);
//
//        Graph<Character> graph = new Graph<>(5);
//
//        graph.addNode(nodeA);
//        graph.addNode(nodeB);
//        graph.addNode(nodeC);
//        graph.addNode(nodeD);
//        graph.addNode(nodeE);
//
//        Dijkstra<Character> dijkstra = new Dijkstra<>();
//
//        graph = dijkstra.calculateShortestPathFromSource(graph, nodeA);
//
//        System.out.println(dijkstra.showPath(graph, nodeA, nodeB));
//
//        for (int i = 0; i < graph.getNodes().size(); i++) {
//
//            System.out.println(dijkstra.showPath(graph, nodeA, graph.getNodes().get(i)));
//        }
//    }
}