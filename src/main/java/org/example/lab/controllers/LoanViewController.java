package org.example.lab.controllers;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.lab.loans.LoanPayment;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanViewController {
    @FXML
    private TableView<LoanPayment> tableView = new TableView<>();

    @FXML
    private LineChart<String, Number> lineChart;

    private Optional<List<LoanPayment>> payments = Optional.empty();
    private Optional<List<LoanPayment>> filteredPayments = Optional.empty();

    @FXML
    private Label saveLabel;

    @FXML
    private TextField fromYear;
    @FXML
    private TextField fromMonth;
    @FXML
    private TextField toYear;
    @FXML
    private TextField toMonth;

    @FXML
    private Label filteringErrorLabel;
    PauseTransition filteringErrorLabelPauseTransition = new PauseTransition(Duration.seconds(2));

    private void initializeChart(List<LoanPayment> payments) {
        lineChart.setTitle("Paskolos įmokos");
        lineChart.getXAxis().setLabel("Mėnesis");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.setName("Loan payments");

        for (LoanPayment payment : payments) {
            series.getData().add(new XYChart.Data<>(String.valueOf((payment.year() - 1) * 12 + payment.month()), payment.getMonthlyPayment()));
        }

        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    public void initializeTable(List<LoanPayment> payments, boolean updatePayments) {
        if (updatePayments) this.payments = Optional.of(payments);

        TableColumn<LoanPayment, Integer> column1 = new TableColumn<>("Metai");
        column1.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<LoanPayment, Integer> column2 = new TableColumn<>("Mėnesis");
        column2.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<LoanPayment, Double> column3 = new TableColumn<>("Paskolos likutis");
        column3.setCellValueFactory(new PropertyValueFactory<>("loanBalance"));

        TableColumn<LoanPayment, Double> column4 = new TableColumn<>("Mėnesinė įmoka");
        column4.setCellValueFactory(new PropertyValueFactory<>("monthlyPayment"));

        TableColumn<LoanPayment, Double> column5 = new TableColumn<>("Palūkanos");
        column5.setCellValueFactory(new PropertyValueFactory<>("interest"));

        TableColumn<LoanPayment, Double> column6 = new TableColumn<>("Kreditas");
        column6.setCellValueFactory(new PropertyValueFactory<>("credit"));

        tableView.getColumns().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);

        tableView.getItems().clear();
        for (LoanPayment payment : payments) {
            tableView.getItems().add(payment);
        }

        initializeChart(payments);
    }

    @FXML
    protected void saveToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("loan.csv"));

        if (payments.isEmpty()) {
            writer.write("No data to save");
            writer.close();

            return;
        }

        writer.write("Metai,Mėnesis,Paskolos likutis,Mėnesinė įmoka,Palūkanos,Kreditas\n");

        for (LoanPayment payment : payments.get()) {
            writer.write(payment.year() + "," + payment.month() + "," + payment.getLoanBalance() + "," + payment.getMonthlyPayment() + "," + payment.getInterest() + "," + payment.getCredit() + "\n");
        }

        writer.close();

        saveLabel.setText("Išsaugota!");

        PauseTransition pauseTransition = new PauseTransition(Duration.seconds(2));
        pauseTransition.setOnFinished(actionEvent -> saveLabel.setText(""));
        pauseTransition.play();
    }

    @FXML
    protected void filter() {
        filteringErrorLabelPauseTransition.setOnFinished(actionEvent -> filteringErrorLabel.setText(""));
        filteringErrorLabelPauseTransition.playFromStart();

        int fromYear;
        try {
            if (this.fromYear.getText().isBlank())
                throw new NullPointerException();

            fromYear = Integer.parseInt(this.fromYear.getText());
        } catch (NullPointerException e) {
            filteringErrorLabel.setText("Turi būti pasirinkti pradžios metai");
            return;
        } catch (NumberFormatException e) {
            filteringErrorLabel.setText("Pradžios metų lauke galimi tik skaičiai");
            return;
        }

        int fromMonth;
        try {
            if (this.fromMonth.getText().isBlank())
                throw new NullPointerException();

            fromMonth = Integer.parseInt(this.fromMonth.getText());
        } catch (NullPointerException e) {
            filteringErrorLabel.setText("Turi būti pasirinktas pradžios mėnesis");
            return;
        } catch (NumberFormatException e) {
            filteringErrorLabel.setText("Pradžios mėnesio lauke galimi tik skaičiai");
            return;
        }

        int toYear;
        try {
            if (this.toYear.getText().isBlank())
                throw new NullPointerException();

            toYear = Integer.parseInt(this.toYear.getText());
        } catch (NullPointerException e) {
            filteringErrorLabel.setText("Turi būti pasirinkti pabaigos metai");
            return;
        } catch (NumberFormatException e) {
            filteringErrorLabel.setText("Pabaigos metų lauke galimi tik skaičiai");
            return;
        }

        int toMonth;
        try {
            if (this.toMonth.getText().isBlank())
                throw new NullPointerException();

            toMonth = Integer.parseInt(this.toMonth.getText());
        } catch (NullPointerException e) {
            filteringErrorLabel.setText("Turi būti pasirinktas pabaigos mėnesis");
            return;
        } catch (NumberFormatException e) {
            filteringErrorLabel.setText("Pabaigos mėnesio lauke galimi tik skaičiai");
            return;
        }

        if (payments.isPresent()) {
            if (fromYear < 1) {
                filteringErrorLabel.setText("Pradžios metai negali būti mažiau už 0");
                return;
            }

            if (fromMonth < 1 || fromMonth > 12) {
                filteringErrorLabel.setText("Pradžios mėnesis turi būti tarp 1 ir 12");
                return;
            }

            if (toMonth < 1 || toMonth > 12) {
                filteringErrorLabel.setText("Pabaigos mėnesis turi būti tarp 1 ir 12");
                return;
            }

            if ((fromYear - 1) * 12 + fromMonth - 1 > (toYear - 1) * 12 + toMonth - 1) {
                filteringErrorLabel.setText("Pradžios data negali būti didesnė už pabaigos datą");
                return;
            }

            if ((toYear - 1) * 12 + toMonth > payments.get().size()) {
                filteringErrorLabel.setText("Pabaigos data negali būti didesnė už paskolos laikotarpį");
                return;
            }

            filteredPayments.ifPresent(List::clear);
            if (filteredPayments.isEmpty()) filteredPayments = Optional.of(new ArrayList<>());

            for (int i = (fromYear - 1) * 12 + fromMonth - 1; i < (toYear - 1) * 12 + toMonth; i++)
            {
                filteredPayments.get().add(payments.get().get(i));
            }
            initializeTable(filteredPayments.get(), false);
        }
    }

    @FXML
    protected void newLoan(ActionEvent event) throws IOException {
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/lab/main-view.fxml"));
        BorderPane borderPane = loader.load();

        Scene scene = new Scene(borderPane);
        stage.setScene(scene);
        stage.show();
    }
}
