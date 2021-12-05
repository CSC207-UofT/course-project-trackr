package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.EventCreator
import com.trackr.trackr_app.manager.EventManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventCreatorBinding {
    @Binds
    abstract fun bindEventCreator(
        eventCreatorImpl: EventManager
    ): EventCreator
}