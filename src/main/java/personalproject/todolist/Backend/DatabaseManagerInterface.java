package personalproject.todolist.Backend;

import personalproject.todolist.ToDo;

import java.io.Closeable;
import java.util.List;

public interface DatabaseManagerInterface {
    /**
     * Opens a connection with the database.
     */
    public void connect();

    /**
     * Creates the necessary tables in the database.
     */
    public void createTables();

    /**
     * Truncates the tables in the database.
     */
    public void clearTables();

    /**
     * Drops the tables in the database.
     */
    public void deleteTables();

    /**
     * Adds a new group to the ToDo group table.
     * @param groupName Name of the new ToDo group.
     */
    public void insertGroup(String groupName);

    /**
     * Removes a group from the ToDo group table.
     * @param groupName Name of the ToDo group.
     */
    public void deleteGroup(String groupName);

    /**
     * Check if there is a group with the given title in the database.
     * @param groupName Title of the ToDo group.
     * @return True if there is a group with the given title, false otherwise.
     */
    public boolean groupExists(String groupName);

    /**
     * Inserts a ToDo object into the database.
     * @param todo ToDo to be inserted.
     */
    public void insertToDo(ToDo todo);

    /**
     * Selects ToDo from tables given ToDo title.
     * @param todoTitle Title of the ToDo.
     * @return ToDo object with the provided title.
     */
    public ToDo selectToDo(String todoTitle);

    /**
     * Selects all ToDos from the table and returns them in a list.
     * @return List of all ToDos.
     */
    public List<ToDo> selectAllTodos();

    /**
     * Selects all ToDos with the given group title from the table.
     * @param groupTitle Title of the ToDo group.
     * @return List of all ToDos from the group.
     */
    public List<ToDo> selectAllTodosFromGroup(String groupTitle);
}
