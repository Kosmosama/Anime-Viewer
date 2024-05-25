module luis.animelist {
    requires javafx.controls;
    requires javafx.fxml;

    opens animelist.main to javafx.fxml;
    opens animelist.common.user_related to javafx.base;
    opens animelist.common.list_related to javafx.base;
    opens animelist.common.other to javafx.base;

    exports animelist.main;
}