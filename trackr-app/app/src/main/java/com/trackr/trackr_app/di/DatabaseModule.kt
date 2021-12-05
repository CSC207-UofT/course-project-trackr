package com.trackr.trackr_app.di

import android.content.Context
import com.trackr.trackr_app.database.EventDao
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.database.TrackrDatabase
import com.trackr.trackr_app.database.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideTrackrDatabase(@ApplicationContext context: Context): TrackrDatabase {
        return TrackrDatabase.getDatabase(context)
    }

    @Provides
    fun provideEventDao(trackrDatabase: TrackrDatabase): EventDao {
        return trackrDatabase.eventDao()
    }

    @Provides
    fun providePersonDao(trackrDatabase: TrackrDatabase): PersonDao {
        return trackrDatabase.personDao()
    }

    @Provides
    fun provideUserDao(trackrDatabase: TrackrDatabase): UserDao {
        return trackrDatabase.userDao()
    }
}