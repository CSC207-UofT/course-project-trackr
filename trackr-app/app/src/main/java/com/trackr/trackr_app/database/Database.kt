package com.trackr.trackr_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trackr.trackr_app.model.Event
import com.trackr.trackr_app.model.Person
import com.trackr.trackr_app.model.User

// TODO: add Person::class, Event::class once implemented

@Database(entities = [User::class, Person::class, Event::class], version = 1, exportSchema = false)
abstract class TrackrDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun personDao(): PersonDao

    abstract fun eventDao(): EventDao

    companion object {
        @Volatile
        private var INSTANCE: TrackrDatabase? = null

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