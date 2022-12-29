package personalproject.todolist.Backend;

public class DatabaseManagerFactory {
    enum DatabaseType {SQLITE}
    public DatabaseManagerInterface getDatabaseManager(DatabaseType dbType) {
        return switch (dbType) {
            case SQLITE -> new DatabaseManagerSQLite();
        };
    }
}
