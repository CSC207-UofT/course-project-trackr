package com.trackr.trackr_app.manager

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