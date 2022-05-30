package ui;

import extras.OriginalGraph;
import model.Dijkstra;
import model.Graph;
import model.Node;

import java.util.*;

public class Menu {

    private final Scanner sc;
//    private List<Node<String>> nodeList;
    private Graph<String> graph;

    public Menu() {
        sc = new Scanner(System.in);
//        nodeList = new ArrayList<>();
    }

    public void mainMenu() {

        System.out.println("\n\n========MAIN MENU========");

        System.out.println("\n-Graph?: " + (graph != null));

        System.out.println(
                "\nSelect an option:\n" +
                        "(1) to create a Graph\n" +
                        "(2) to apply Dijkstra's algorithm\n" +

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
                mainMenu();
                break;

            case 1:
                inputData();
                mainMenu();
                break;

            case 2:
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

        ArrayList<Node<String>> nodeList = new ArrayList<>();

//        for (int i = 0; i < n; i++) {
//
//            System.out.println("\n-Node " + i);
//            System.out.print("Value: ");
//            String value = sc.nextLine();
//
//            Node<String> node = new Node<>(value);
//            nodeList.add(node);
//        }

        System.out.print("Nodes: ");
        String nodesString = sc.nextLine();

        String[] nodeValues = nodesString.split(";");

        for (String s : nodeValues) {

            Node<String> newNode = new Node<>(s);
            nodeList.add(newNode);
        }

        for (Node<String> node : nodeList) {

            System.out.print("\nAdjacency list for " + node.getValue() + ": ");
            String line = sc.nextLine();

            String[] array = line.split(" ");

            for (String s : array) {

                String[] line1 = s.split(";");

                if (!s.isEmpty()) {

                    String value = line1[0];
                    double distance = Double.parseDouble(line1[1]);

                    Node<String> destinationNode = findNode(nodeList, value);

//                System.out.println("\naNode: " + aNode);

                    if (destinationNode != null) {

                        node.addDestination(destinationNode, distance);

                    } else {

                        System.out.println("--Node " + value + " doesn't exist");
                    }
                }
            }
        }

        graph = new Graph<>(n);
        graph.getNodes().addAll(nodeList);

//        System.out.println();
//        System.out.println(graph);
    }

    private void dijkstraData() {

        graph.resetNodes();

        System.out.print("\nSource Node: ");
        String value = sc.nextLine();

        Node<String> sourceNode = findNode(graph.getNodes(), value);
        Dijkstra<String> dijkstra = new Dijkstra<>();

        if (sourceNode != null) {

            graph = dijkstra.calculateShortestPathFromSource(graph, sourceNode);

//        System.out.println("\nORIGINAL " + originalGraph);
//        System.out.println("\nAUX " + auxGraph);

            allShortestPaths(graph, sourceNode, dijkstra);

        } else {

            System.out.println("--Node " + value + " doesn't exist");
        }
    }

    private void allShortestPaths(Graph<String> auxGraph, Node<String> sourceNode, Dijkstra<String> dijkstra) {

        System.out.println("\nShortest paths: ");

        for (Node<String> node : auxGraph.getNodes()) {

//            System.out.println("-" + dijkstra.showPath(auxGraph, sourceNode, node));
            System.out.println("-" + dijkstra.showPath(sourceNode, node));
        }
    }

    private Node<String> findNode(ArrayList<Node<String>> nodes, String parameter) {

        for (Node<String> node : nodes) {

            if (node.getValue().equals(parameter)) {

                return node;
            }
        }

        return null;
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

    private void originalGraph() {

        OriginalGraph<Integer> g = new OriginalGraph<>();

//        g.addEdge(0, 1, true);
//        g.addEdge(0, 4, true);
//        g.addEdge(1, 2, true);
//        g.addEdge(1, 3, true);
//        g.addEdge(1, 4, true);
//        g.addEdge(2, 3, true);
//        g.addEdge(3, 4, true);

        System.out.println(g);
    }
}