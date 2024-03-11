package org.example.lab.hello_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private VBox rootVBox;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onByeButtonClick() {
        welcomeText.setText("Bye from JavaFX Application!");
    }

    @FXML
    protected void loadAnotherView() throws IOException {
        URL url = getClass().getResource("/org/example/lab/another-view.fxml");
        if (url == null) {
            throw new IOException("FXML file not found!");
        }

        VBox vBox = FXMLLoader.load(url);
        rootVBox.getChildren().setAll(vBox);
    }
}