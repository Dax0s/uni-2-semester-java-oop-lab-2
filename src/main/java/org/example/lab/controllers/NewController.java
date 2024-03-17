package org.example.lab.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.lab.loans.LoanPayment;

import java.util.List;

public class NewController {
    @FXML
    private TableView<LoanPayment> tableView = new TableView<>();

    @FXML
    private LineChart<String, Number> lineChart;

    private void initializeChart(List<LoanPayment> payments) {
        lineChart.setTitle("Loan payments left");
        lineChart.getXAxis().setLabel("Month");

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        series.setName("Loan payments left");

        for (LoanPayment payment : payments) {
            series.getData().add(new XYChart.Data<>(String.valueOf((payment.year() - 1) * 12 + payment.month()), payment.getLeftToPay()));
        }

        lineChart.getData().add(series);
    }

    public void initializeTable(List<LoanPayment> payments) {
        TableColumn<LoanPayment, Integer> column1 = new TableColumn<>("Year");
        column1.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn<LoanPayment, Integer> column2 = new TableColumn<>("Month");
        column2.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<LoanPayment, Double> column3 = new TableColumn<>("Sum to pay");
        column3.setCellValueFactory(new PropertyValueFactory<>("sumToPay"));

        TableColumn<LoanPayment, Double> column4 = new TableColumn<>("Left to pay");
        column4.setCellValueFactory(new PropertyValueFactory<>("leftToPay"));

        tableView.getColumns().clear();
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        for (LoanPayment payment : payments) {
            tableView.getItems().add(payment);
        }

        initializeChart(payments);
    }

    @FXML
    protected void onButtonClick() {
    }
}
