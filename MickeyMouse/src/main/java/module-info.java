module com.example.mickeymouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.View to javafx.fxml;
    exports com.example.View;
}