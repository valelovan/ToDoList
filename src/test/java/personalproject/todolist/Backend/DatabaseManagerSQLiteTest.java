package personalproject.todolist.Backend;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import personalproject.todolist.Backend.DatabaseManagerSQLite;
import personalproject.todolist.Group;

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
    private ResultSet testQueryResult;
    private DatabaseMetaData testMetaData;
    private Statement testStatement;

    @BeforeAll
    public void initSetup() {
        testConnection = mock(Connection.class);
        testDB = DatabaseManagerSQLite.getInstance();
        testDB.connection = testConnection;
        testResultSet = mock(ResultSet.class);
        testQueryResult = mock(ResultSet.class);
        testMetaData = mock(DatabaseMetaData.class);
        testStatement = mock(Statement.class);
    }

    @BeforeEach
    public void beforeEach() {
        testConnection = mock(Connection.class);
        testDB = DatabaseManagerSQLite.getInstance();
        testDB.connection = testConnection;
        testResultSet = mock(ResultSet.class);
        testQueryResult = mock(ResultSet.class);
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

    @Test
    public void testClearTablesNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.clearTables());

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testClearTablesNoTables() throws SQLException {
        setTablesDoNotExist();

        assertThrows(IllegalStateException.class, () -> testDB.clearTables());

        verifyTablesExist();
    }

    @Test
    public void testClearTablesTables() throws SQLException {
        String todoSQL = """
                DELETE FROM Todos""";
        String groupSQL = """
                Delete FROM \"Groups\"""";
        setTablesExist();
        when(testStatement.executeBatch()).thenReturn(new int[] {1,1});

        when(testConnection.createStatement()).thenReturn(testStatement);

        testDB.clearTables();

        verify(testStatement, times(1)).addBatch(todoSQL);
        verify(testStatement, times(1)).addBatch(groupSQL);
        verify(testStatement, times(1)).executeBatch();
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    // TESTS: public void deleteTables();

    @Test
    public void testDeleteTablesNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.deleteTables());

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testDeleteTablesNoTables() throws SQLException {
        setTablesDoNotExist();

        assertThrows(IllegalStateException.class, () -> testDB.deleteTables());

        verifyTablesExist();
    }

    @Test
    public void testDeleteTablesTables() throws SQLException {
        String todoSQL = """
                DROP TABLE Todos""";
        String groupSQL = """
                DROP TABLE \"Groups\"""";
        setTablesExist();
        when(testStatement.executeBatch()).thenReturn(new int[] {1,1});

        when(testConnection.createStatement()).thenReturn(testStatement);

        testDB.deleteTables();

        verify(testStatement, times(1)).addBatch(todoSQL);
        verify(testStatement, times(1)).addBatch(groupSQL);
        verify(testStatement, times(1)).executeBatch();
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    // TESTS: public void insertGroup(Group group);

    @Test
    public void testInsertGroupNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);
        Group tempGroup = new Group(1234,"School", "School work due.");

        assertThrows(IllegalStateException.class, () -> testDB.insertGroup(tempGroup));

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testInsertGroupNoTables() throws SQLException {
        setTablesDoNotExist();
        Group tempGroup = new Group(1234,"School", "School work due.");

        assertThrows(IllegalStateException.class, () -> testDB.insertGroup(tempGroup));

        verifyTablesExist();
    }

    @Test
    public void testInsertGroupTablesExist() throws SQLException {
        String insertSQL = """
                INSERT INTO \"Groups\" (Name, Description) VALUES ('School', 'School work due.')""";
        Group tempGroup = new Group(1234,"School", "School work due.");
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.execute(insertSQL)).thenReturn(false);

        testDB.insertGroup(tempGroup);

        verify(testStatement, times(1)).execute(insertSQL);
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    @Test
    public void testInsertGroupTablesExistNullGroup() throws SQLException {
        setTablesExist();
        Group tempGroup = null;

        assertThrows(IllegalArgumentException.class, () -> testDB.insertGroup(tempGroup));

        verifyTablesExist();
    }

    // TESTS: public void deleteGroup(Group group);

    @Test
    public void testDeleteGroupNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);
        Group tempGroup = new Group(1234,"School", "School work due.");

        assertThrows(IllegalStateException.class, () -> testDB.deleteGroup(tempGroup));

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testDeleteGroupNoTables() throws SQLException {
        setTablesDoNotExist();
        Group tempGroup = new Group(1234, "School", "School work due.");

        assertThrows(IllegalStateException.class, () -> testDB.deleteGroup(tempGroup));

        verifyTablesExist();
    }

    @Test
    public void testDeleteGroupTablesExist() throws SQLException {
        String deleteSQL = """
                DELETE FROM \"Groups\" WHERE ID = 1234""";
        Group tempGroup = new Group(1234, "School", "School work due.");
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.execute(deleteSQL)).thenReturn(false);

        testDB.deleteGroup(tempGroup);

        verify(testStatement, times(1)).execute(deleteSQL);
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    @Test
    public void testDeleteGroupTablesExistNullGroup() throws SQLException {
        setTablesExist();
        Group tempGroup = null;

        assertThrows(IllegalArgumentException.class, () -> testDB.deleteGroup(tempGroup));

        verifyTablesExist();
    }

    // TESTS: public List<Group> selectAllGroups();

    @Test
    public void testSelectAllGroupsNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.selectAllGroups());

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testSelectAllGroupsNoTables() throws SQLException {
        setTablesDoNotExist();

        assertThrows(IllegalStateException.class, () -> testDB.selectAllGroups());

        verifyTablesExist();
    }

    @Test
    public void testSelectAllGroupsTablesExistGroups() throws SQLException {
        String selectAllSQL = """
                SELECT * FROM \"Groups\"""";
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.executeQuery(selectAllSQL)).thenReturn(testQueryResult);
        when(testQueryResult.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(testQueryResult.getInt("ID")).thenReturn(1234).thenReturn(4321);
        when(testQueryResult.getString("Name")).thenReturn("School").thenReturn("Job");
        when(testQueryResult.getString("Description")).thenReturn("School work due.").thenReturn("Work tasks.");

        List<Group> tempGroups = testDB.selectAllGroups();
        assertNotNull(tempGroups);
        // Index 0
        assertEquals(1234, tempGroups.get(0).getId());
        assertEquals("School", tempGroups.get(0).getName());
        assertEquals("School work due.", tempGroups.get(0).getDescription());
        // Index 1
        assertEquals(4321, tempGroups.get(1).getId());
        assertEquals("Job", tempGroups.get(1).getName());
        assertEquals("Work tasks.", tempGroups.get(1).getDescription());

        verify(testStatement, times(1)).executeQuery(selectAllSQL);
        verify(testQueryResult, times(1)).next();
        verify(testQueryResult, times(1)).getInt("ID");
        verify(testQueryResult, times(1)).getString("Name");
        verify(testQueryResult, times(1)).getString("Description");
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    @Test
    public void testSelectAllGroupsTablesExistNoGroups() throws SQLException {
        String selectAllSQL = """
                SELECT * FROM \"Groups\"""";
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.executeQuery(selectAllSQL)).thenReturn(testQueryResult);
        when(testQueryResult.next()).thenReturn(false);

        List<Group> tempGroups = testDB.selectAllGroups();
        assertNotNull(tempGroups);
        assertTrue(tempGroups.isEmpty());

        verify(testStatement, times(1)).executeQuery(selectAllSQL);
        verify(testQueryResult, times(1)).next();
        verify(testQueryResult, never()).getInt("ID");
        verify(testQueryResult, never()).getString("Name");
        verify(testQueryResult, never()).getString("Description");
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    // TESTS: public Group selectGroup(int id);

    @Test
    public void testSelectGroupNotConnected() throws SQLException {
        when(testConnection.isClosed()).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> testDB.selectGroup(1234));

        verify(testConnection, times(1)).isClosed();
    }

    @Test
    public void testSelectGroupNoTables() throws SQLException {
        setTablesDoNotExist();

        assertThrows(IllegalStateException.class, () -> testDB.selectGroup(1234));

        verifyTablesExist();
    }

    @Test
    public void testSelectGroupTablesExistGroupExists() throws SQLException {
        String selectSQL = """
                SELECT * FROM \"Groups\" WHERE ID = 1234""";
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.executeQuery(selectSQL)).thenReturn(testQueryResult);
        when(testQueryResult.next()).thenReturn(true);
        when(testQueryResult.getInt("ID")).thenReturn(1234);
        when(testQueryResult.getString("Name")).thenReturn("School");
        when(testQueryResult.getString("Description")).thenReturn("School work due.");

        Group tempGroup = testDB.selectGroup(1234);
        assertNotNull(tempGroup);
        assertEquals(1234, tempGroup.getId());
        assertEquals("School", tempGroup.getName());
        assertEquals("School work due.", tempGroup.getDescription());

        verify(testStatement, times(1)).executeQuery(selectSQL);
        verify(testQueryResult, times(1)).next();
        verify(testQueryResult, times(1)).getInt("ID");
        verify(testQueryResult, times(1)).getString("Name");
        verify(testQueryResult, times(1)).getString("Description");
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

    @Test
    public void testSelectGroupTablesExistGroupDoesNotExist() throws SQLException {
        String selectSQL = """
                SELECT * FROM \"Groups\" WHERE ID = 1234""";
        setTablesExist();

        when(testConnection.createStatement()).thenReturn(testStatement);
        when(testStatement.executeQuery(selectSQL)).thenReturn(testQueryResult);
        when(testQueryResult.next()).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> testDB.selectGroup(1234));

        verify(testStatement, times(1)).executeQuery(selectSQL);
        verify(testQueryResult, times(1)).next();
        verify(testQueryResult, never()).getInt("ID");
        verify(testQueryResult, never()).getString("Name");
        verify(testQueryResult, never()).getString("Description");
        verify(testConnection, never()).rollback();

        verifyTablesExist();
    }

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
        verify(testMetaData, atMost(1))
                .getTables(null, null, "Groups", null);
        verify(testResultSet, atMost(2)).next();
    }
}
