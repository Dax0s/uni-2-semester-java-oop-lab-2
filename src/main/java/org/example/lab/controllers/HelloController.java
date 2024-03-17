package org.example.lab.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.lab.loans.AnnuityLoan;
import org.example.lab.loans.LinearLoan;
import org.example.lab.loans.Loan;
import org.example.lab.loans.LoanPayment;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class HelloController {

    private final ObservableList<String> choicesList = FXCollections.observableArrayList("Anuiteto", "Linijinis");

    @FXML
    private BorderPane rootBorderPane;

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

    private Stage stage;
    private Scene scene;

    @FXML
    private void initialize() {
        choices.setItems(choicesList);
        choices.setValue(choicesList.get(0));
    }

    @FXML
    protected void onCalculateButtonPress(ActionEvent event) throws IOException {
        Optional<Double> sum = Optional.empty();
        try {
            if (this.sum.getText().isBlank())
                throw new NullPointerException();

            sum = Optional.of(Double.parseDouble(this.sum.getText()));
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

        Optional<Double> yearlyPercentage = Optional.empty();
        try {
            if (this.yearlyPercentage.getText().isBlank())
                throw new NullPointerException();

            yearlyPercentage = Optional.of(Double.parseDouble(this.yearlyPercentage.getText()));
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

            List<LoanPayment> payments = loan.GetMonthlyPayments();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/lab/table-view.fxml"));
            BorderPane borderPane = loader.load();


            NewController controller = loader.getController();
            controller.initializeTable(payments);

            stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
            scene = new Scene(borderPane);
            stage.setScene(scene);
            stage.show();
        }
    }
}