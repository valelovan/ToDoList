package personalproject.todolist.Backend;

import personalproject.todolist.ToDo;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public class DatabaseManagerSQLite implements DatabaseManagerInterface, Closeable {


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
