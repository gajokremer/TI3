package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ControllerGUI {

    public ControllerGUI() {

    }

    @FXML
    private Pane mainPane;

    @FXML
    private ImageView ivLogo;

    @FXML
    void start(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        fxmlLoader.setController(this);
        Parent menu = fxmlLoader.load();
        mainPane.getChildren().setAll(menu);

//        Image logo = new Image("PlaneLogo.heic");
        Image logo = new Image("https://www.seekpng.com/png/full/105-1051251_cartoon-airplane-png.png");
        ivLogo.setImage(logo);
    }
}