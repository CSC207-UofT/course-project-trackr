package com.trackr.trackr_app.di

import android.content.Context
import com.trackr.trackr_app.notification.EventNotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationModule {

    @Singleton
    @Provides
    fun provideEventNotificationManager(
            @ApplicationContext context: Context): EventNotificationManager {
        return EventNotificationManager(context)
    }


}