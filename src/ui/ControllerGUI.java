package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.Graph;
import model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ControllerGUI {

    private Graph<String> graph;
    private int size;
    private boolean[] assigned;

    public ControllerGUI() {
    }

    @FXML
    private Pane mainPane;

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
    void start(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);

//        Image logo = new Image("https://www.seekpng.com/png/full/105-1051251_cartoon-airplane-png.png");
//        ivLogo.setImage(logo);
    }

    @FXML
    void createFlightSystem(ActionEvent event) throws IOException {

        if (!tfSystemSize.getText().isEmpty()) {

            size = Integer.parseInt(tfSystemSize.getText());

            if (size <= 50) {

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

                showWarningDialogue("System creation error", "The system can't exceed size 50.");
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

                assigned = new boolean[size];
                Arrays.fill(assigned, false);

                taDestinations.setEditable(false);

                showSuccessDialogue("Addition successful", "All destinations have been added");

            } else {

                showWarningDialogue("Destinations addition error",
                        "There must be " + size + " destinations.");
                taDestinations.setText("");
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
                Node<String> origin = graph.getNode(graph.getNodes(), originData);

                int originIndex = graph.getNodes().indexOf(origin);

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

                                Node<String> destinationNode = graph.getNode(graph.getNodes(), destinationData);

                                if (destinationNode != null) {

                                    origin.addDestination(destinationNode, distance);
                                    assigned[originIndex] = true;
                                    tfDestinationName.setText("");
                                    taConnections.setText("");

                                } else {

                                    showWarningDialogue("Adjacency assignation error",
                                            "Destination " + destinationData + "doesn't exist.");
                                }
                            }
                        }

                        if (taConnections.getText().isEmpty()) {

                            assigned[originIndex] = true;
                            tfDestinationName.setText("");
                        }

                        if (assigned[originIndex]) {

                            showSuccessDialogue("Adjacency assignation successful",
                                    "Connections for '" + originData + "' have been assigned.");

                            System.out.println(origin.getAdjacentNodes());
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