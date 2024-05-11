module luis.animelist {
    requires javafx.controls;
    requires javafx.fxml;


    opens animelist.main to javafx.fxml;
    exports animelist.main;
}