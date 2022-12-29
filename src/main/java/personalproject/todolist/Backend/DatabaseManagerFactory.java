package personalproject.todolist.Backend;

public class DatabaseManagerFactory {
    public DatabaseManagerInterface getDatabaseManager(String dbType) {
        return switch (dbType) {
            case "SQLite" -> new DatabaseManagerSQLite();
            default -> throw new IllegalArgumentException("Invalid database type. Currently [" + dbType +
                    "] is unsupported.");
        };
    }
}
