package org.example.lab.hello_view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class HelloController {

    @FXML
    private TextField sum;
    @FXML
    private TextField years;
    @FXML
    private TextField months;

    @FXML
    private Label sumError;
    @FXML
    private Label yearError;
    @FXML
    private Label monthError;

    @FXML
    protected void onCalculateButtonPress() {
        Optional<Float> sum = Optional.empty();
        try {
            if (this.sum.getText().isBlank())
                throw new NullPointerException();

            sum = Optional.of(Float.parseFloat(this.sum.getText()));
            sumError.setText("");
        } catch (NullPointerException e) {
            sumError.setText("Šis laukas negali būti tuščias!");
        } catch (NumberFormatException e) {
            sumError.setText("Šis laukas priima tik skaičius!");
        }

        Optional<Integer> years = Optional.empty();
        try {
            if (this.years.getText().isBlank())
                throw new NullPointerException();

            years = Optional.of(Integer.valueOf(this.years.getText()));
            yearError.setText("");
        } catch (NullPointerException e) {
            yearError.setText("Šis laukas negali būti tuščias!");
        } catch (NumberFormatException e) {
            yearError.setText("Šis laukas priima tik skaičius!");
        }

        Optional<Integer> months = Optional.empty();
        try {
            if (this.months.getText().isBlank())
                throw new NullPointerException();

            months = Optional.of(Integer.valueOf(this.months.getText()));
            monthError.setText("");

            if (months.get() < 0 || months.get() > 12)
                monthError.setText("Mėnesių laukas gali turėti vertes nuo 0 iki 12!");

        } catch (NullPointerException e) {
            monthError.setText("Šis laukas negali būti tuščias!");
        } catch (NumberFormatException e) {
            monthError.setText("Šis laukas priima tik skaičius!");
        }


        System.out.println("---------");
        System.out.println(sum.isPresent() ? sum.get() : "No sum");
        System.out.println(years.isPresent() ? years.get() : "No years");
        System.out.println(months.isPresent() ? months.get() : "No months");
        System.out.println("---------");
    }

//    @FXML
//    protected void loadAnotherView() throws IOException {
//        URL url = getClass().getResource("/org/example/lab/another-view.fxml");
//        if (url == null) {
//            throw new IOException("FXML file not found!");
//        }
//
//        VBox vBox = FXMLLoader.load(url);
//        rootVBox.getChildren().setAll(vBox);
//    }
}