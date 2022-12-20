module personalproject.todolist {
    requires javafx.controls;
    requires javafx.fxml;


    opens personalproject.todolist to javafx.fxml;
    exports personalproject.todolist;
}