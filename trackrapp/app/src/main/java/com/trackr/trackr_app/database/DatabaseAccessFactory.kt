package database

/**
 * A factory class that is responsible for creating/return DatabaseAccessInterfaces
 */
object DatabaseAccessFactory {
    fun createDatabaseAccessInterface(): DatabaseAccessInterface {
        return DataAccess()
    }
}