module org.example.oop {
    requires javafx.controls;
    requires javafx.fxml;


    opens application to javafx.fxml;
    exports application;
    exports application.Interface;
    opens application.Interface to javafx.fxml;
}