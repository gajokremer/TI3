package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest {

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

    public Graph<String> setUpScenario4() {

        Graph<String> graph = new Graph<>(7);

        Node<String> nodeA = new Node<>("A");
        Node<String> nodeB = new Node<>("B");
        Node<String> nodeC = new Node<>("C");
        Node<String> nodeD = new Node<>("D");
        Node<String> nodeE = new Node<>("E");
        Node<String> nodeF = new Node<>("F");
        Node<String> nodeS = new Node<>("S");

        nodeA.addDestination(nodeE, 0);
        nodeA.addDestination(nodeD, 4);

        nodeB.addDestination(nodeA, 3);

        nodeC.addDestination(nodeD, 2);

        nodeD.addDestination(nodeE, 1);
        nodeD.addDestination(nodeF, 2);

        nodeE.addDestination(nodeB, 2);
        nodeE.addDestination(nodeF, 2);

        nodeS.addDestination(nodeC, 3);
        nodeS.addDestination(nodeB, 1);

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);
        graph.addNode(nodeS);

        return graph;
    }

    @Test
    void traverse() {

        Graph<Integer> graph = setUpScenario1();
        Node<Integer> sourceNode = graph.getSpecificNode(0);

        BreadthFirstSearch<Integer> bfs = new BreadthFirstSearch<Integer>(sourceNode);
        bfs.traverse();

        StringBuilder paths = new StringBuilder();

        assertEquals(0, bfs.getVisitedNodes().get(0).getData());
        assertEquals(2, bfs.getVisitedNodes().get(4).getData());
        assertEquals(5, bfs.getVisitedNodes().size());


        Graph<String> stringGraph = setUpScenario4();
        Node<String> nodeA = stringGraph.getSpecificNode("A");

        BreadthFirstSearch<String> bfs1 = new BreadthFirstSearch<String>(nodeA);
        bfs1.traverse();

//        int indexOfA = stringGraph.getNodes().indexOf(nodeA);
//        int indexOfB = stringGraph.getNodes().indexOf(stringGraph.getSpecificNode("B"));

        assertEquals("A", bfs1.getVisitedNodes().get(0).getData());
        assertEquals(5, bfs1.getVisitedNodes().size());
    }
}