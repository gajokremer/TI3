package ui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.Graph;

import java.io.IOException;
import java.util.ArrayList;

public class ControllerGUI {

    private Graph<String> graph;

    public ControllerGUI() {
    }

    @FXML
    private Pane mainPane;

    @FXML
    private TextField tfSystemSize;

    @FXML
    private TextArea taConnections;

    @FXML
    private TextArea taDestinations;

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

            int size = Integer.parseInt(tfSystemSize.getText());

            if (size <= 50) {

//                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SystemCreator.fxml"));
//                fxmlLoader.setController(this);
//                Parent menu = fxmlLoader.load();
//                mainPane.getChildren().setAll(menu);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SystemCreator.fxml"));
                fxmlLoader.setController(this);
                DialogPane dialoguePane = fxmlLoader.load();

                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setDialogPane(dialoguePane);
                dialog.showAndWait();

                tfSystemSize.setText("");

            } else {

                showWarningDialogue("System creation error", "The system can't exceed size 50.");
                tfSystemSize.setText("");
            }

        } else {

            showWarningDialogue("System creation error", "A size must be provided.");
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