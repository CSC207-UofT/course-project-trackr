package com.trackr.trackr_app.dependency_injection

import com.trackr.trackr_app.manager.EventCreator
import com.trackr.trackr_app.manager.EventManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/*
This abstract class is used by hilt.
Binds the EventCreator interface to the EventRepository.
When EventCreator is injected into a constructor, this binding allows hilt to know that
EventRepository is the implementation of EventAccessor
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class EventCreatorBinding {
    @Binds
    abstract fun bindEventCreator(
        eventCreatorImpl: EventManager
    ): EventCreator
}