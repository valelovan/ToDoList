package personalproject.todolist.Backend;

import personalproject.todolist.Group;
import personalproject.todolist.ToDo;

import java.sql.*;
import java.io.Closeable;
import java.util.*;

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

    private boolean isConnected() {
        try {
            return null != connection && !connection.isClosed();
        } catch (SQLException s) {throw new RuntimeException(s);}
    }

    private boolean tableExists(String tableName) {
        ResultSet result = null;
        boolean exists = false;
        try {
            DatabaseMetaData dbMeta = connection.getMetaData();
            result = dbMeta.getTables(null, null, tableName, null);
            exists = result.next();
        } catch (Exception e) {/* NOTHING */}
        finally {
            try {result.close();} catch (Exception e) {/* NOTHING */}
        }
        return exists;
    }

    private boolean tablesExist() {
        return tableExists("Todos") || tableExists("Groups");
    }

    @Override
    public void close() {
        if (!isConnected()) throw new IllegalStateException("No connection in progress.");
        try {
            connection.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void connect() {
        if (isConnected()) throw new IllegalStateException("Connection already in progress.");
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME);
        } catch (Exception e) {throw new RuntimeException(e);}
    }

    @Override
    public void createTables() {
        if (!isConnected()) throw new IllegalStateException("No connection in progress.");
        if (tablesExist()) throw new IllegalStateException("Tables already exist.");
        String todoCreateSQL = """
                CREATE TABLE Todos (ID Integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, GroupID Int(31) NOT NULL,
                    Title VarChar(255) NOT NULL, Description VarChar(4095) NOT NULL,  IsComplete BOOLEAN NOT NULL,
                	CONSTRAINT "GroupID_fk" FOREIGN KEY(GroupID) REFERENCES "Groups" (ID) ON DELETE CASCADE);""";
        String groupCreateSQL = """
                CREATE TABLE "Groups" (ID Integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,
                    Name VarChar(255) NOT NULL UNIQUE, Description VarChar(4095) NOT NULL);""";

        try (Statement statement = connection.createStatement()) {
            statement.addBatch(todoCreateSQL);
            statement.addBatch(groupCreateSQL);
            statement.executeBatch();
        } catch (Exception e) {throw new RuntimeException(e);}
    }

    @Override
    public void clearTables() {
        if (!isConnected()) throw new IllegalStateException("No connection in progress.");
        if (!tablesExist()) throw new IllegalStateException("Tables do not exist.");
        String todoSQL = """
                DELETE FROM Todos""";
        String groupSQL = """
                Delete FROM \"Groups\"""";
        try (Statement statement = connection.createStatement()) {
            statement.addBatch(todoSQL);
            statement.addBatch(groupSQL);
            statement.executeBatch();
        } catch (Exception e ) {throw new RuntimeException(e);}
    }

    @Override
    public void deleteTables() {
        if (!isConnected()) throw new IllegalStateException("No connection in progress.");
        if (!tablesExist()) throw new IllegalStateException("Tables do not exist.");
        String todoSQL = """
                DROP TABLE Todos""";
        String groupSQL = """
                DROP TABLE \"Groups\"""";
        try (Statement statement = connection.createStatement()) {
            statement.addBatch(todoSQL);
            statement.addBatch(groupSQL);
            statement.executeBatch();
        } catch (Exception e ) {throw new RuntimeException(e);}
    }

    @Override
    public void executeSQL(String sql) {

    }

    @Override
    public List<ToDo> executeToDoSQL(String sql) {
        return null;
    }

    @Override
    public List<Group> executeGroupSQL(String sql) {
        return null;
    }

}
