package ui;

import model.BreadthFirstSearch;
import model.Dijkstra;
import model.Graph;
import model.Node;

import java.util.*;

public class Menu {

    private final Scanner sc;
    private Graph<String> graph;

    public Menu() {
        sc = new Scanner(System.in);
    }

    public void mainMenu() {

        System.out.println("\n\n========MAIN MENU========");

        System.out.println("\n-Graph?: " + (graph != null));

        System.out.println(
                "\nSelect an option:\n" +
                        "(1) to create a Graph\n" +
                        "(2) to traverse by Breadth First Search\n" +
                        "(3) to apply Dijkstra's algorithm\n" +

                        "\n(0) to exit");

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {

            default:

                System.out.println("\n---Invalid Option");
                mainMenu();
                break;

            case -1:
                quickGraph();
                mainMenu();
                break;

            case 0:
                System.out.println("\n-----OPERATION ENDED-----\n");
                break;

            case 1:
                inputData();
                mainMenu();
                break;

            case 2:
                bfsData();
                sc.nextLine();
                mainMenu();
                break;

            case 3:
                dijkstraData();
                sc.nextLine();
                mainMenu();
                break;
        }
    }

    private void inputData() {

        System.out.print("\nTotal Nodes: ");
        int n = sc.nextInt();
        sc.nextLine();

        graph = new Graph<>(n);

        List<Node<String>> nodeList = new ArrayList<>();

//        for (int i = 0; i < n; i++) {
//
//            System.out.println("\n-Node " + i);
//            System.out.print("Value: ");
//            String value = sc.nextLine();
//
//            Node<String> node = new Node<>(value);
//            nodeList.add(node);
//        }

        System.out.println("(Format: Node1;Node2;Node3...)");
        System.out.print("Nodes: ");
        String nodesString = sc.nextLine();

        String[] nodeValues = nodesString.split(";");

        for (String s : nodeValues) {

            Node<String> newNode = new Node<>(s);
            nodeList.add(newNode);
        }

        System.out.println("\n(Adjacency List format: Dest1;Dist1 Dest2;Dist2...)");
        System.out.println("(If no specific distance, type '-')");

        for (Node<String> node : nodeList) {

            System.out.print("\nAdjacency list for " + node.getData() + ": ");
            String line = sc.nextLine();

            String[] array = line.split(" ");

            for (String s : array) {

                String[] otherLine = s.split(";");

                if (!s.isEmpty()) {

                    String data = otherLine[0];
                    double distance;

                    if (otherLine[1].equals("-")) {

                        distance = Double.POSITIVE_INFINITY;

                    } else {

                        distance = Double.parseDouble(otherLine[1]);
                    }

//                    Node<String> destinationNode = graph.getSpecificNode(nodeList, data);

                    Node<String> destinationNode = graph.getNodeFromList(nodeList, data);

                    if (destinationNode != null) {

                        node.addDestination(destinationNode, distance);

                    } else {

                        System.out.println("--Node " + data + " doesn't exist");
                    }
                }
            }
        }

        graph.getNodes().addAll(nodeList);
    }

    private void bfsData() {

        graph.resetNodes();

        System.out.print("\nSource Node: ");
        String data = sc.nextLine();

//        Node<String> sourceNode = graph.getSpecificNode(graph.getNodes(), data);
        Node<String> sourceNode = graph.getSpecificNode(data);

        if (sourceNode != null){

            BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(sourceNode);
            bfs.traverse();

            System.out.println("\nNode List: ");

            for (Node<String> node : bfs.getVisitedNodes()) {

                System.out.println("-" + node);
            }

        } else {

            System.out.println("--Node " + data + " doesn't exist");
        }
    }

    private void dijkstraData() {

        graph.resetNodes();

        System.out.print("\nSource Node: ");
        String data = sc.nextLine();

        Node<String> sourceNode = graph.getSpecificNode(data);
        Dijkstra<String> dijkstra = new Dijkstra<>();

        if (sourceNode != null) {

//            graph = dijkstra.calculateShortestPathFromSource(graph, sourceNode);
            dijkstra.calculateShortestPathFromSource(graph, sourceNode);

            allShortestPaths(graph, sourceNode, dijkstra);

            System.out.println();
            for (Node<String> node : graph.getNodes()) {

                System.out.println(node.getData() + ": " + node.getShortestPath());
            }

        } else {

            System.out.println("--Node " + data + " doesn't exist");
        }
    }

    private void allShortestPaths(Graph<String> aGraph, Node<String> sourceNode, Dijkstra<String> dijkstra) {

        System.out.println("\nShortest paths: ");

        for (Node<String> node : aGraph.getNodes()) {

//            System.out.println("-" + dijkstra.showPath(auxGraph, sourceNode, node));
            System.out.println("-" + dijkstra.printPath(sourceNode, node));
        }
    }

    public void quickGraph() {

        graph = new Graph<>(6);

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
    }
}