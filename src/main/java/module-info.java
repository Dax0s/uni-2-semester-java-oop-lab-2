module org.example.lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.lab to javafx.fxml;
    exports org.example.lab;
    exports org.example.lab.controllers;
    opens org.example.lab.controllers to javafx.fxml;
    exports org.example.lab.loans;
    opens org.example.lab.loans to javafx.fxml;
}