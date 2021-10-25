package database;

/**
 * A factory class that is responsible for creating/return DatabaseAccessInterfaces
 */
public class DatabaseAccessFactory {
    public static DatabaseAccessInterface createDatabaseAccessInterface() {
        return new DataAccess();
    }
}
