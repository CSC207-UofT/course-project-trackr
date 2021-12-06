package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.TrackrEvent
import com.trackr.trackr_app.model.User


/**
 * Initializes and manages the broader SQL database.
 * Provides access to data access objects which can
 * be used to interact with models within the database.
 */
@Database(
    entities = [User::class, Person::class, TrackrEvent::class],
    version = 1,
    exportSchema = false
)
abstract class TrackrDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun personDao(): PersonDao

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: TrackrDatabase? = null

        /**
         * Build the database and initialize it as a singleton
         * @param context The app context the database exists within
         */
        fun getDatabase(context: Context): TrackrDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackrDatabase::class.java,
                    "trackr_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}