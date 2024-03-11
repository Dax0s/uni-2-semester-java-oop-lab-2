package org.example.lab.new_view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NewController {
    @FXML
    private Label label;

    private int clicks = 0;

    @FXML
    protected void onButtonClick() {
        label.setText("Clicks: " + ++clicks);
    }
}
