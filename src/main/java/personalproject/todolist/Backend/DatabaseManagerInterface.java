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
     * Selects all Group titles from the tables.
     * @return List of Group titles.
     */
    public List<String> selectAllGroups();

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
     * Selects all ToDos that are related to the ToDo associated with the given ID.
     * @param id ID of the specific ToDo.
     * @return List of all related ToDos.
     */
    public List<ToDo> selectRelatedToDos(int id);

    /**
     * Inserts a one-way relationship between the two given ToDos
     * (Second is related to first, but not vice versa).
     * @param firstToDo First ToDo object.
     * @param secondToDo Second ToDo Object.
     */
    public void insertRelationship(ToDo firstToDo, ToDo secondToDo);

    /**
     * Inserts a mutual relationship between the two given ToDos.
     * @param firstToDo First ToDo object.
     * @param secondToDo Second ToDo Object.
     */
    public void insertMutualRelationship(ToDo firstToDo, ToDo secondToDo);

    /**
     * Checks if two ToDos have a one-way relationship with each other.
     * @param firstToDo First ToDo object.
     * @param secondToDo Second ToDo object.
     * @return True if a one-way relationship exists, false otherwise.
     */
    public boolean hasRelationship(ToDo firstToDo, ToDo secondToDo);

    /**
     * Checks if two ToDos have a mutual relationship with each other.
     * @param firstToDo First ToDo object.
     * @param secondToDo Second ToDo object.
     * @return True if a mutual relationship exists, false otherwise.
     */
    public boolean hasMutualRelationship(ToDo firstToDo, ToDo secondToDo);
}
