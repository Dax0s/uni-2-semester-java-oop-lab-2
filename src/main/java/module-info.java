module org.example.lab {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.lab to javafx.fxml;
    exports org.example.lab;
    exports org.example.lab.hello_view;
    opens org.example.lab.hello_view to javafx.fxml;
    exports org.example.lab.new_view;
    opens org.example.lab.new_view to javafx.fxml;
}