package org.example.lab.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.lab.loans.AnnuityLoan;
import org.example.lab.loans.LinearLoan;
import org.example.lab.loans.Loan;

import java.util.Optional;

public class HelloController {

    private final ObservableList<String> choicesList = FXCollections.observableArrayList("Anuiteto", "Linijinis");

    @FXML
    private TextField sum;
    @FXML
    private TextField years;
    @FXML
    private TextField months;
    @FXML
    private TextField yearlyPercentage;

    @FXML
    private Label sumError;
    @FXML
    private Label yearError;
    @FXML
    private Label monthError;
    @FXML
    private Label yearlyPercentageError;

    @FXML
    private ChoiceBox<String> choices;

    @FXML
    private void initialize() {
        choices.setItems(choicesList);
        choices.setValue(choicesList.get(0));
    }

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

        Optional<Float> yearlyPercentage = Optional.empty();
        try {
            if (this.yearlyPercentage.getText().isBlank())
                throw new NullPointerException();

            yearlyPercentage = Optional.of(Float.parseFloat(this.yearlyPercentage.getText()));
            yearlyPercentageError.setText("");
        } catch (NullPointerException e) {
            yearlyPercentageError.setText("Šis laukas negali būti tuščias!");
        } catch (NumberFormatException e) {
            yearlyPercentageError.setText("Šis laukas priima tik skaičius!");
        }

        if (sum.isPresent() && years.isPresent() && months.isPresent() && yearlyPercentage.isPresent()) {
            Loan loan;

            switch (choices.getValue()) {
                case "Anuiteto":
                    loan = new AnnuityLoan(sum.get(), years.get(), months.get(), yearlyPercentage.get());
                case "Linijinis":
                    loan = new LinearLoan(sum.get(), years.get(), months.get(), yearlyPercentage.get());
                default:
                    loan = new AnnuityLoan(sum.get(), years.get(), months.get(), yearlyPercentage.get());
            }
        }
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