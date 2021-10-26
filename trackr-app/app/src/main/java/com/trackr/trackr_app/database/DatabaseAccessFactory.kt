package database

import database.DatabaseAccessInterface
import database.DataAccess

/**
 * A factory class that is responsible for creating/return DatabaseAccessInterfaces
 */
object DatabaseAccessFactory {
    @JvmStatic
    fun createDatabaseAccessInterface(): DatabaseAccessInterface {
        return DataAccess()
    }
}