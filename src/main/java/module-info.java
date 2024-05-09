module luis.animelist {
    requires javafx.controls;
    requires javafx.fxml;


    opens luis.animelist to javafx.fxml;
    exports luis.animelist;
}