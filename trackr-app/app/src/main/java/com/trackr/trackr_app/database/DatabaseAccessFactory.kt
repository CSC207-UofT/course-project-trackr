package database

import com.trackr.trackr_app.database.DataAccess

/**
 * A factory class that is responsible for creating/return DatabaseAccessInterfaces
 */
object DatabaseAccessFactory {
    @JvmStatic
    fun createDatabaseAccessInterface(): DatabaseAccessInterface {
        return DataAccess()
    }
}