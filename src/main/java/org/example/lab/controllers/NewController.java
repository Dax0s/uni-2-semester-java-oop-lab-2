package org.example.lab.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.lab.loans.LoanPayment;

import java.util.List;

public class NewController {
    @FXML
    private Label label;

    @FXML
    TableView<LoanPayment> tableView = new TableView<>();

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
    }

    @FXML
    protected void onButtonClick() {
    }
}
