package ui;

import extras.OriginalGraph;
import model.Dijkstra;
import model.Graph;
import model.Node;

import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc;

    public Menu() {
        sc = new Scanner(System.in);
    }

    public void mainMenu() {

        System.out.println("\n\n========MAIN MENU========\n");

        System.out.println(
                "Select an option:\n" +
                        "(1) to input data\n" +

                        "\n(0) to exit");

        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {

            default:

                System.out.println("\n---Invalid Option");
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
        }
    }

    private void inputData() {

        System.out.print("\nTotal Nodes: ");
        int n = sc.nextInt();
        sc.nextLine();

        List<Node<String>> nodes = new ArrayList<>();

        for (int i = 0; i < n; i++) {

            System.out.println("\n-Node " + i);
            System.out.print("Value: ");
            String value = sc.nextLine();

            Node<String> node = new Node<>(value);
            nodes.add(node);
        }

        System.out.println();
        System.out.println(nodes);

        Graph<String> stringGraph = new Graph<>(n);

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

    public void test() {

        Graph<Character> characterGraph = new Graph<Character>(6);

        Node<Character> nodeA = new Node<>('A');
        Node<Character> nodeB = new Node<>('B');
        Node<Character> nodeC = new Node<>('C');
        Node<Character> nodeD = new Node<>('D');
        Node<Character> nodeE = new Node<>('E');
        Node<Character> nodeF = new Node<>('F');

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

        characterGraph.addNode(nodeA);
        characterGraph.addNode(nodeB);
        characterGraph.addNode(nodeC);
        characterGraph.addNode(nodeD);
        characterGraph.addNode(nodeE);
        characterGraph.addNode(nodeF);

        Dijkstra<Character> dijkstra = new Dijkstra<>();

        characterGraph = dijkstra.calculateShortestPathFromSource(characterGraph, nodeA);

        for (Node<Character> node : characterGraph.getNodes()) {

            System.out.println(dijkstra.showPath(characterGraph, nodeA, node));
        }
    }
}