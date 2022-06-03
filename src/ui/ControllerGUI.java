package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.BreadthFirstSearch;
import model.Dijkstra;
import model.Graph;
import model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControllerGUI {

    private Graph<String> graph;
    private int size;
//    private boolean[] assigned;

    public ControllerGUI() {
    }

    @FXML
    private Pane mainPane;

    @FXML
    private Label lbGraph;

    @FXML
    private TextField tfSystemSize;

    @FXML
    private TextField tfSystemSize2;

    @FXML
    private TextArea taConnections;

    @FXML
    private TextArea taDestinations;

    @FXML
    private TextField tfDestinationName;

    @FXML
    private TextArea taResult;

    @FXML
    private TextField tfStartingPoint;


    @FXML
    void start(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);


//        graph = new Graph<>(7);
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


        lbGraph.setText(String.valueOf(graph != null));
    }

    @FXML
    void createFlightSystem(ActionEvent event) throws IOException {

        if (!tfSystemSize.getText().isEmpty()) {

            size = Integer.parseInt(tfSystemSize.getText());

            if (size <= 50 && size > 0) {

                graph = new Graph<>(size);

                tfSystemSize.setText("");

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SystemCreator.fxml"));
                fxmlLoader.setController(this);
                DialogPane dialoguePane = fxmlLoader.load();

                tfSystemSize2.setText(String.valueOf(size));
                taDestinations.setEditable(true);

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialoguePane);
                dialog.showAndWait();

            } else {

                showWarningDialogue("System creation error", "The system size must be between 1 and 50.");
                tfSystemSize.setText("");
            }

        } else {

            showWarningDialogue("System creation error", "A size must be provided.");
        }
    }

    @FXML
    void addDestinations(ActionEvent event) throws InterruptedException {

        if (!taDestinations.getText().isEmpty()) {

            String[] destinations = taDestinations.getText().split("\n");

            if (destinations.length == size) {

                List<Node<String>> nodeList = new ArrayList<>();

                for (String s : destinations) {

                    Node<String> newNode = new Node<>(s);
                    nodeList.add(newNode);
                }

                graph.getNodes().addAll(nodeList);

//                assigned = new boolean[size];
//                Arrays.fill(assigned, false);

                taDestinations.setEditable(false);

                lbGraph.setText(String.valueOf(graph != null));

                showSuccessDialogue("Addition successful", "All destinations have been added");

            } else {

                showWarningDialogue("Destinations addition error",
                        "There must be " + size + " destinations.");
//                taDestinations.setText("");
            }

        } else {

                showWarningDialogue("Destinations addition error","Destinations must be provided.");
        }
    }

    @FXML
    void assign(ActionEvent event) {

        if (!graph.getNodes().isEmpty()) {

            if (!tfDestinationName.getText().isEmpty()) {

                String originData = tfDestinationName.getText();
//                Node<String> origin = graph.getSpecificNode(graph.getNodes(), originData);
                Node<String> origin = graph.getSpecificNode(originData);

                int originIndex = graph.getNodes().indexOf(origin);

                boolean[] assigned = new boolean[size];
                Arrays.fill(assigned, false);

                if (!assigned[originIndex]) {

                    if (origin != null) {

                        if (!taConnections.getText().isEmpty()) {

                            String[] connections = taConnections.getText().split("\n");

                            for (String s : connections) {

                                String[] line = s.split(";");

                                String destinationData = line[0];
                                double distance;

                                if (line[1].equals("-")) {

                                    distance = Double.POSITIVE_INFINITY;

                                } else {

                                    distance = Double.parseDouble(line[1]);
                                }

//                                Node<String> destinationNode = graph.getNodeFromList(graph.getNodes(), destinationData);
                                Node<String> destinationNode = graph.getSpecificNode(destinationData);

                                if (distance >= 0) {

                                    if (destinationNode != null) {

                                        origin.addDestination(destinationNode, distance);
                                        assigned[originIndex] = true;

                                    } else {

                                        assigned[originIndex] = false;
                                        showWarningDialogue("Adjacency assignation error",
                                                "Destination '" + destinationData + "' doesn't exist.");
                                    }

                                } else {

                                    showWarningDialogue("Adjacency assignation error",
                                            "The distance must be a positive number.");
                                }
                            }
                        }

                        if (taConnections.getText().isEmpty()) {

                            assigned[originIndex] = true;
                            tfDestinationName.setText("");
                        }

                        if (assigned[originIndex]) {

                            tfDestinationName.setText("");
                            taConnections.setText("");
                            showSuccessDialogue("Adjacency assignation successful",
                                    "Connections for '" + originData + "' have been assigned.");
                        }

                    } else {

                        showWarningDialogue("Adjacency assignation error",
                                "Destination " + originData + "doesn't exist.");
                    }

                } else {

                    showWarningDialogue("Adjacency assignation error",
                            "Connections have already been assigned for " + "'" + originData + "'");
                }
            }

        } else {

            showWarningDialogue("Adjacency assignation error",
                    "No destinations have been added");
        }
    }

    @FXML
    void moreOptions(ActionEvent event) throws IOException {

        if (graph != null) {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MoreOptions.fxml"));
            fxmlLoader.setController(this);
            DialogPane dialoguePane = fxmlLoader.load();

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(dialoguePane);
            dialog.showAndWait();

        } else {

            showWarningDialogue("More Options error",
                    "There must be an existing system first.");
        }
    }

    @FXML
    void traverse(ActionEvent event) {

        if (!tfStartingPoint.getText().isEmpty()) {

            graph.resetNodes();

            String sourceData = tfStartingPoint.getText();
            Node<String> source = graph.getSpecificNode(sourceData);

//            System.out.println();
//            System.out.println(source != null);

            if (source != null) {

                BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(source);
                bfs.traverse();

//                System.out.println(source.getAdjacentNodes());
//                System.out.println(bfs.getVisitedNodes());

                StringBuilder paths = new StringBuilder();

                for (int i = 0; i < bfs.getVisitedNodes().size(); i++) {

                    if ((i+1) != 1) paths.append("\n");

                    paths.append(i + 1).append(". ").append(bfs.getVisitedNodes().get(i).getData());
                }

                taResult.setText(String.valueOf(paths));

            } else {

                showWarningDialogue("Optimization error", "This destination doesn't exist.");
            }

        } else {

            showWarningDialogue("Optimization error", "A starting point must be provided.");
        }
    }

    @FXML
    void optimize(ActionEvent event) {

        if (!tfStartingPoint.getText().isEmpty()) {

            graph.resetNodes();

            String sourceData = tfStartingPoint.getText();
            Node<String> source = graph.getSpecificNode(sourceData);

            if (source != null) {

                Dijkstra<String> dijkstra = new Dijkstra<>();
                dijkstra.calculateShortestPathFromSource(graph, source);

                StringBuilder paths = new StringBuilder();

                for (Node<String> node : graph.getNodes()) {

                    if (graph.getNodes().indexOf(node) != 0) paths.append("\n");

                    paths.append(dijkstra.printPath(source, node));
                }

                taResult.setText(paths.toString());

            } else {

                showWarningDialogue("Optimization error", "This destination doesn't exist.");
            }

        } else {

            showWarningDialogue("Optimization error", "A starting point must be provided.");
        }
    }

    @FXML
    void close(ActionEvent event) throws IOException {

        Platform.exit();
    }

    public void showSuccessDialogue(String header, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Data Base");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showWarningDialogue(String header, String message) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Data Base");
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}