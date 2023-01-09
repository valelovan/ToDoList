package personalproject.todolist.Backend;

import personalproject.todolist.Group;
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
     * @param group The new ToDo group.
     */
    public void insertGroup(Group group);

    /**
     * Removes a group from the ToDo group table.
     * @param group The ToDo group.
     */
    public void deleteGroup(Group group);

    /**
     * Selects all Group titles from the tables.
     * @return List of all Groups.
     */
    public List<Group> selectAllGroups();

    /**
     * Selects a group with the given ID in the database.
     * @param id ID of the specific group.
     * @return The group if it exists, throws an exception otherwise.
     */
    public Group selectGroup(int id);

    /**
     * Inserts a ToDo object into the database.
     * @param todo ToDo to be inserted.
     */
    public void insertToDo(ToDo todo);

    /**
     * Selects ToDos from tables given ToDos title (titles might not be unique).
     * This method will look for the title as a substring of all titles.
     * @param todoTitle Title of the ToDos.
     * @return List of ToDo objects with the provided title.
     */
    public List<ToDo> selectToDos(String todoTitle);

    /**
     * Deletes ToDo from tables given ToDo ID.
     * @param id ID of the ToDo.
     */
    public void deleteToDo(int id);

    /**
     * Selects all ToDos from the table and returns them in a list.
     * @return List of all ToDos.
     */
    public List<ToDo> selectAllTodos();

    /**
     * Selects all ToDos with the given group title from the table and returns
     * them in a list.
     * @param groupTitle Title of the ToDo group.
     * @return List of all ToDos from the group.
     */
    public List<ToDo> selectAllTodos(String groupTitle);

    /**
     * Selects all ToDos which are not completed.
     * @return List of all incomplete ToDos.
     */
    public List<ToDo> selectAllIncompleteToDos();
}
