package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    public Graph<Integer> setUpScenario1() {

        Graph<Integer> graph = new Graph<>(5);

        Node<Integer> node0 = new Node<>(0);
        Node<Integer> node1 = new Node<>(1);
        Node<Integer> node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);

        node0.addDestination(node1, 3);
        node0.addDestination(node3, 7);
        node0.addDestination(node4, 8);

        node1.addDestination(node2, 1);
        node1.addDestination(node3, 4);

        node3.addDestination(node2, 2);

        node4.addDestination(node3, 3);

        graph.addNode(node0);
        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);

        return graph;
    }

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

    @Test
    void addNode() {

        Graph<Integer> graph = setUpScenario1();

        assertFalse(graph.addNode(new Node<>(5)));

        graph = new Graph<>(2);
        assertTrue(graph.addNode(new Node<>(0)));
        assertTrue(graph.addNode(new Node<>(1)));
        assertFalse(graph.addNode(new Node<>(2)));
    }

    @Test
    void getSpecificNode() {

        Graph<String> graph = setUpScenario2();

        assertEquals("A", graph.getSpecificNode("A").getData());

        graph = new Graph<>(2);
        assertNull(graph.getSpecificNode("F"));
    }

    @Test
    void getNodeFromList() {

        Graph<Integer> graph = new Graph<>(3);
        List<Node<Integer>> nodeList = new ArrayList<>();

        nodeList.add(new Node<>(0));
        nodeList.add(new Node<>(1));
        nodeList.add(new Node<>(2));

        Node<Integer> nodeFromList = graph.getNodeFromList(nodeList, 2);

        assertEquals(2, nodeFromList.getData());
    }

    @Test
    void resetNodes() {

        Graph<Integer> graph = setUpScenario1();

        Dijkstra<Integer> dijkstra = new Dijkstra<>();
        dijkstra.calculateShortestPathFromSource(graph, graph.getSpecificNode(0));

        for (Node<Integer> node : graph.getNodes()) {

            assertNotEquals(Double.POSITIVE_INFINITY, node.getDistance());
        }

        graph.resetNodes();

        for (Node<Integer> node : graph.getNodes()) {

            assertEquals(Double.POSITIVE_INFINITY, node.getDistance());
        }
    }
}