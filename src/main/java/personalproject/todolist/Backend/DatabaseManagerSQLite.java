package personalproject.todolist.Backend;

import personalproject.todolist.ToDo;

import java.sql.*;
import java.io.Closeable;
import java.util.List;

public class DatabaseManagerSQLite implements DatabaseManagerInterface, Closeable {

    /**
     * The singleton's instance.
     */
    private static DatabaseManagerSQLite instance;

    /**
     * DatabaseManagerSQLite is a singleton. Must use this method to retrieve
     * the instance of the database manager (this creates a global access point
     * and prevents multiple instances trying to access the database).
     * @return Instance of the database manager.
     */
    public static DatabaseManagerSQLite getInstance() {
        if (null == instance) instance = new DatabaseManagerSQLite();
        return instance;
    }


    /**
     * The database name.
     */
    private final String DATABASE_NAME = "Todos.sqlite3";
    /**
     * Database connection.
     */
    protected Connection connection;

    private DatabaseManagerSQLite() {
        // Private constructor for singleton design pattern
    }


    @Override
    public void close() {

    }

    @Override
    public void connect() {

    }

    @Override
    public void createTables() {

    }

    @Override
    public void clearTables() {

    }

    @Override
    public void deleteTables() {

    }

    @Override
    public void insertGroup(String groupName) {

    }

    @Override
    public void deleteGroup(String groupName) {

    }

    @Override
    public List<String> selectAllGroups() {
        return null;
    }

    @Override
    public boolean groupExists(String groupName) {
        return false;
    }

    @Override
    public void insertToDo(ToDo todo) {

    }

    @Override
    public List<ToDo> selectToDos(String todoTitle) {
        return null;
    }

    @Override
    public void deleteToDo(int id) {

    }

    @Override
    public List<ToDo> selectAllTodos() {
        return null;
    }

    @Override
    public List<ToDo> selectAllTodos(String groupTitle) {
        return null;
    }

    @Override
    public List<ToDo> selectAllIncompleteToDos() {
        return null;
    }
}
