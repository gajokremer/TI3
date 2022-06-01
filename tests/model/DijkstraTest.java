package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraTest {

//    public Graph<Integer> setUpScenario1() {
//
//        Graph<Integer> graph = new Graph<>(5);
//
//        Node<Integer> node0 = new Node<>(0);
//        Node<Integer> node1 = new Node<>(1);
//        Node<Integer> node2 = new Node<>(2);
//        Node<Integer> node3 = new Node<>(3);
//        Node<Integer> node4 = new Node<>(4);
//
//        node0.addDestination(node1, 3);
//        node0.addDestination(node3, 7);
//        node0.addDestination(node4, 8);
//
//        node1.addDestination(node2, 1);
//        node1.addDestination(node3, 4);
//
//        node3.addDestination(node2, 2);
//
//        node4.addDestination(node3, 3);
//
//        graph.addNode(node0);
//        graph.addNode(node1);
//        graph.addNode(node2);
//        graph.addNode(node3);
//        graph.addNode(node4);
//
//        return graph;
//    }

    public Graph<String> setUpScenario2() {

        Graph<String> graph = new Graph<>(5);

        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");

        nodeA.addDestination(nodeD, 60);
        nodeA.addDestination(nodeC, 12);

        nodeB.addDestination(nodeA, 10);

        nodeC.addDestination(nodeD, 32);
        nodeC.addDestination(nodeB, 20);

        nodeE.addDestination(nodeA, 7);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);

        return graph;
    }
    
    public Graph<String> setUpScenario3() {

        Graph<String> graph = new Graph<>(6);

        Node<String > nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");

        nodeA.addDestination(nodeB, 6);
        nodeA.addDestination(nodeC, 90);
        nodeA.addDestination(nodeF, 70);

        nodeB.addDestination(nodeD, 41);

        nodeC.addDestination(nodeB, 12);
        nodeC.addDestination(nodeF, 10);

        nodeD.addDestination(nodeC, 12);

        nodeE.addDestination(nodeA, 2);
        nodeE.addDestination(nodeB, 22);
        nodeE.addDestination(nodeC, 60);
        nodeE.addDestination(nodeD, 50);

        nodeF.addDestination(nodeE, 15);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        return graph;
    }

//    public Graph<String> setUpScenario4() {
//
//        Graph<String> graph = new Graph<>(7);
//
//        Node<String> nodeA = new Node<>("A");
//        Node<String> nodeB = new Node<>("B");
//        Node<String> nodeC = new Node<>("C");
//        Node<String> nodeD = new Node<>("D");
//        Node<String> nodeE = new Node<>("E");
//        Node<String> nodeF = new Node<>("F");
//        Node<String> nodeS = new Node<>("S");
//
//        nodeA.addDestination(nodeE, 0);
//        nodeA.addDestination(nodeD, 4);
//
//        nodeB.addDestination(nodeA, 3);
//
//        nodeC.addDestination(nodeD, 2);
//
//        nodeD.addDestination(nodeE, 1);
//        nodeD.addDestination(nodeF, 2);
//
//        nodeE.addDestination(nodeB, 2);
//        nodeE.addDestination(nodeF, 2);
//
//        nodeS.addDestination(nodeC, 3);
//        nodeS.addDestination(nodeB, 1);
//
//        graph.addNode(nodeA);
//        graph.addNode(nodeB);
//        graph.addNode(nodeC);
//        graph.addNode(nodeD);
//        graph.addNode(nodeE);
//        graph.addNode(nodeF);
//        graph.addNode(nodeS);
//
//        return graph;
//    }

    @Test
    void calculateShortestPathFromSource() {

        Graph<String> graph = setUpScenario2();
        Node<String> nodeA = graph.getNodes().get(0);
        Node<String> nodeE = graph.getNodes().get(4);

        Dijkstra<String> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPathFromSource(graph, nodeA);

        assertEquals(32, graph.getSpecificNode("B").getDistance());
        assertEquals(12, graph.getSpecificNode("C").getDistance());
        assertEquals(Double.POSITIVE_INFINITY, graph.getSpecificNode("E").getDistance());

//        From A to A: 0.0
//        From A to B: 32.0
//        From A to C: 12.0
//        From A to D: 44.0
//        From A to E: Infinity

        graph.resetNodes();
        System.out.println("\n");

        dijkstra.calculateShortestPathFromSource(graph, nodeE);

        assertEquals(7, graph.getSpecificNode("A").getDistance());
        assertEquals(51, graph.getSpecificNode("D").getDistance());
        assertEquals(0, graph.getSpecificNode("E").getDistance());
//
//        From E to A: 7.0
//        From E to B: 39.0
//        From E to C: 19.0
//        From E to D: 51.0
//        From E to E: 0.0
    }

    @Test
    void printPath() {

        Graph<String> graph = setUpScenario3();
        Node<String> sourceNode = graph.getSpecificNode("A");

        Dijkstra<String> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPathFromSource(graph, sourceNode);

        String expectedReturn = "From A to B: 6.0";
        String actualReturn = dijkstra.printPath(sourceNode, graph.getSpecificNode("B"));

        assertEquals(expectedReturn, actualReturn);
    }
}