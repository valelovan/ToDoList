module personalproject.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens personalproject.todolist to javafx.fxml;
    exports personalproject.todolist;
}