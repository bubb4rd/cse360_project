module com.example.cse360_phase2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.cse360_phase2 to javafx.fxml;
    exports com.example.cse360_phase2;
}