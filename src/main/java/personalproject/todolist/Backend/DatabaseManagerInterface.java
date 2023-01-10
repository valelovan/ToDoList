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
     * Executes SQL without returning anything.
     * @param sql SQL statement to execute.
     */
    public void executeSQL(String sql);

    /**
     * Executes the SQL and returns a list of ToDos from the query.
     * @param sql SQL statement to execute
     * @return List of ToDos objects.
     */
    public List<ToDo> executeToDoSQL(String sql);

    /**
     * Executes the SQL and returns a list of Groups from the query.
     * @param sql SQL statement to execute.
     * @return List of Groups objects.
     */
    public List<Group> executeGroupSQL(String sql);

}
