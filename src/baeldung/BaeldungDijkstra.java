package baeldung;

import java.util.*;

public class BaeldungDijkstra {

    public BaeldungGraph calculateShortestPathFromSource(BaeldungGraph graph, BaeldungGraph.Node source) {

        source.setDistance(0);

        Set<BaeldungGraph.Node> settledNodes = new HashSet<>();
//        Set<Graph.Node> unsettledNodes = new HashSet<>();
        PriorityQueue<BaeldungGraph.Node> unsettledNodes = new PriorityQueue<>();

        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {

            BaeldungGraph.Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);

//            for (Map.Entry<Node, Integer> adjacencyPair :
            for (Map.Entry<BaeldungGraph.Node, Integer> adjacencyPair :
                    currentNode.getAdjacentNodes().entrySet()) {

                BaeldungGraph.Node adjacentNode = adjacencyPair.getKey();
//                Integer edgeWeight = adjacencyPair.getValue();
                int edgeWeight = adjacencyPair.getValue();

                if (!settledNodes.contains(adjacentNode)) {

                    calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }

            settledNodes.add(currentNode);
        }

        return graph;
    }

    private void calculateMinimumDistance(BaeldungGraph.Node evaluationNode, Integer edgeWeight, BaeldungGraph.Node sourceNode) {

        Integer sourceDistance = sourceNode.getDistance();

        if (sourceDistance + edgeWeight < evaluationNode.getDistance()) {

            evaluationNode.setDistance(sourceDistance + edgeWeight);

            LinkedList<BaeldungGraph.Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

//    private Graph.Node getLowestDistanceNode(Set<Graph.Node> unsettledNodes) {
    private BaeldungGraph.Node getLowestDistanceNode(PriorityQueue<BaeldungGraph.Node> unsettledNodes) {

        BaeldungGraph.Node lowestDistanceNode = null;
        int lowestDistance  = Integer.MAX_VALUE;

        for (BaeldungGraph.Node node : unsettledNodes) {

            int nodeDistance = node.getDistance();

            if (nodeDistance < lowestDistance) {

                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }

        return lowestDistanceNode;
    }

    public static void main(String[] args) {

//        Graph.Node nodeA = new Graph.Node('A');
//        Graph.Node nodeB = new Graph.Node('B');
//        Graph.Node nodeC = new Graph.Node('C');
//        Graph.Node nodeD = new Graph.Node('D');
//        Graph.Node nodeE = new Graph.Node('E');
//        Graph.Node nodeF = new Graph.Node('F');
//
//        nodeA.addDestination(nodeB, 10);
//        nodeA.addDestination(nodeC, 15);
//
//        nodeB.addDestination(nodeD, 12);
//        nodeB.addDestination(nodeF, 15);
//
//        nodeC.addDestination(nodeE, 10);
//
//        nodeD.addDestination(nodeE, 2);
//        nodeD.addDestination(nodeF, 1);
//
//        nodeF.addDestination(nodeE, 5);
//
//        Graph graph = new Graph();
//
//        graph.addNode(nodeA);
//        graph.addNode(nodeB);
//        graph.addNode(nodeC);
//        graph.addNode(nodeD);
//        graph.addNode(nodeE);
//        graph.addNode(nodeF);

        BaeldungGraph.Node node0 = new BaeldungGraph.Node(0);
        BaeldungGraph.Node node1 = new BaeldungGraph.Node(1);
        BaeldungGraph.Node node2 = new BaeldungGraph.Node(2);
        BaeldungGraph.Node node3 = new BaeldungGraph.Node(3);
        BaeldungGraph.Node node4 = new BaeldungGraph.Node(4);

        node0.addDestination(node1, 3);
        node0.addDestination(node3, 7);
        node0.addDestination(node4, 8);

        node1.addDestination(node2, 1);
        node1.addDestination(node3, 4);

        node3.addDestination(node2, 2);

        node4.addDestination(node3, 3);
//
        BaeldungGraph graph = new BaeldungGraph();

        graph.addNode(node0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        BaeldungDijkstra dijkstra = new BaeldungDijkstra();

        graph = dijkstra.calculateShortestPathFromSource(graph, node0);

        for (int i = 0; i < graph.getNodes().size(); i++) {

            System.out.println("From " + node0.getIndex() + " to " + i + ": " + graph.getNodes().get(i).getDistance());
        }
    }
}