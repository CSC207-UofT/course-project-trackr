package database;

/**
 * A factory class that is responsible for creating/return DatabaseAccessInterfaces
 */
public class DatabaseAccessFactory {
    private final static DatabaseAccessInterface DAO = new DataAccess();

    public static DatabaseAccessInterface createDatabaseAccessInterface() {
        return DatabaseAccessFactory.DAO;
    }
}
