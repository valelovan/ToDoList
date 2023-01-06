package personalproject.todolist.Backend;

import org.junit.jupiter.api.*;
import personalproject.todolist.Backend.DatabaseManagerSQLite;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseManagerSQLiteTest {
    private final String dbName = "Todos.sqlite3";
    private Connection testConnection;
    private DatabaseManagerSQLite testDB;

    @BeforeAll
    public void initSetup() {
        testConnection = mock(Connection.class);
        testDB = DatabaseManagerSQLite.getInstance();
        testDB.connection = testConnection;
    }

    // TESTS: public void close();
    @Test
    public void testCloseNotConnected() {
        //
    }

    @Test
    public void testCloseConnected() {
        //
    }

    // TESTS: public void connect();
    @Test
    public void testConnectNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(false);

        testDB.connect();

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testConnectConnected() {
        //
    }

    // TESTS: public void createTables();


    // TESTS: public void clearTables();


    // TESTS: public void deleteTables();


    // TESTS: public void insertGroup(String groupName);


    // TESTS: public void deleteGroup(String groupName);


    // TESTS: public List<String> selectAllGroups();


    // TESTS: public boolean groupExists(String groupName);


    // TESTS: public void insertToDo(ToDo todo);


    // TESTS: public List<ToDo> selectToDos(String todoTitle);


    // TESTS: public void deleteToDo(int id);


    // TESTS: public List<ToDo> selectAllTodos();


    // TESTS: public List<ToDo> selectAllTodos(String groupTitle);


    // TESTS: public List<ToDo> selectAllIncompleteToDos();
}
