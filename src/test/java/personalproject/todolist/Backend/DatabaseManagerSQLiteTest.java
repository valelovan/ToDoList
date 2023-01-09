package personalproject.todolist.Backend;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import personalproject.todolist.Backend.DatabaseManagerSQLite;
import static org.junit.Assert.*;

import java.sql.*;
import java.util.List;

import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseManagerSQLiteTest {
    private final String dbName = "Todos.sqlite3";
    private Connection testConnection;
    private DatabaseManagerSQLite testDB;
    private ResultSet testResultSet;
    private DatabaseMetaData testMetaData;
    private Statement testStatement;

    @BeforeAll
    public void initSetup() {
        testConnection = mock(Connection.class);
        testDB = DatabaseManagerSQLite.getInstance();
        testDB.connection = testConnection;
        testResultSet = mock(ResultSet.class);
        testMetaData = mock(DatabaseMetaData.class);
        testStatement = mock(Statement.class);
    }

    @BeforeEach
    public void beforeEach() {
        testConnection = mock(Connection.class);
        testDB = DatabaseManagerSQLite.getInstance();
        testDB.connection = testConnection;
        testResultSet = mock(ResultSet.class);
        testMetaData = mock(DatabaseMetaData.class);
        testStatement = mock(Statement.class);
    }

    // TESTS: public void close();
    @Test
    public void testCloseNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.close());

        verify(testConnection, times(1)).isClosed();
        verify(testConnection, never()).close();
    }

    @Test
    public void testCloseConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(false);

        testDB.close();

        verify(testConnection, times(1)).isClosed();
        verify(testConnection, times(1)).close();
    }

    // TESTS: public void connect();
    @Test
    public void testConnectNotConnectedNull() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);
        testDB.connection = null;

        testDB.connect();
        assertNotNull(testDB.connection);

        verify(testConnection, atMost(1)).isClosed();
    }

    @Test
    public void testConnectNotConnectedNotNull() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        testDB.connect();
        assertNotNull(testDB.connection);

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testConnectConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(false);

        assertThrows(IllegalStateException.class, () -> testDB.connect());

        verify(testConnection, times(1)).isClosed();
    }

    // TESTS: public void createTables();
    @Test
    public void testCreateTablesNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.createTables());

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testCreateTablesTablesExist() throws SQLException {
        setTablesExist();

        assertThrows(IllegalStateException.class, () -> testDB.createTables());

        verifyTablesExist();
    }

    @Test
    public void testCreateTablesNoTables() throws SQLException {
        String todoSQL = """
                CREATE TABLE Todos (ID Integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, GroupID Int(31) NOT NULL,
                    Title VarChar(255) NOT NULL, Description VarChar(4095) NOT NULL,  IsComplete BOOLEAN NOT NULL,
                	CONSTRAINT "GroupID_fk" FOREIGN KEY(GroupID) REFERENCES "Groups" (ID) ON DELETE CASCADE);""";
        String groupSQL = """
                CREATE TABLE "Groups" (ID Integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
                    Name VarChar(255) NOT NULL UNIQUE, Description VarChar(4095) NOT NULL);""";
        setTablesDoNotExist();
        when(testStatement.executeBatch()).thenReturn(new int[] {1,1});

        when(testConnection.createStatement()).thenReturn(testStatement);

        testDB.createTables();

        verify(testStatement, times(1)).addBatch(todoSQL);
        verify(testStatement, times(1)).addBatch(groupSQL);
        verify(testStatement, times(1)).executeBatch();
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

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

    // MISC TEST HELPER METHDOS

    private void setTablesExist() throws SQLException {
        when(testConnection.getMetaData()).thenReturn(testMetaData);
        when(testMetaData.getTables(null, null, "Todos", null))
                .thenReturn(testResultSet);
        when(testMetaData.getTables(null, null, "Groups", null))
                .thenReturn(testResultSet);
        when(testResultSet.next()).thenReturn(true);
    }

    private void setTablesDoNotExist() throws SQLException {
        when(testConnection.getMetaData()).thenReturn(testMetaData);
        when(testMetaData.getTables(null, null, "Todos", null))
                .thenReturn(testResultSet);
        when(testMetaData.getTables(null, null, "Groups", null))
                .thenReturn(testResultSet);
        when(testResultSet.next()).thenReturn(false);
    }

    private void verifyTablesExist() throws SQLException {
        verify(testMetaData, times(1))
                .getTables(null, null, "Todos", null);
        verify(testMetaData, times(1))
                .getTables(null, null, "Groups", null);
        verify(testResultSet, times(2)).next();
    }
}
