package ui;

import extras.OriginalGraph;
import model.MyGraph;

public class Menu {

    public void mainMenu() {

        MyGraph<Integer> myGraph = new MyGraph<>();

        myGraph.addEdge(2, 3, false);
        myGraph.addEdge(1, 2, true);

        System.out.println(myGraph.getEdges());
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