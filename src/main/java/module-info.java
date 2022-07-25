module com.example.java2lesson6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.java2lesson6 to javafx.fxml;
    exports com.example.java2lesson6;
    exports com.example.java2lesson6.controllers;
    opens com.example.java2lesson6.controllers to javafx.fxml;
}