module personalproject.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens personalproject.todolist to javafx.fxml;
    exports personalproject.todolist;
}