module com.example.fcdslibraryv2 {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.example.fcdslibraryv2 to javafx.fxml;
    exports com.example.fcdslibraryv2;
}