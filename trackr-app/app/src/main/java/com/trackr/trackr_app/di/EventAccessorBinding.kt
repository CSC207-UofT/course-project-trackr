package com.trackr.trackr_app.di

import com.trackr.trackr_app.repository.EventAccessor
import com.trackr.trackr_app.repository.EventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/*
This abstract class is used by hilt.
Binds the EventAccessor interface to the EventRepository.
When EventAccessor is injected into a constructor, this binding allows hilt to know that
EventRepository is the implementation of EventAccessor
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class EventAccessorBinding {
    @Binds
    abstract fun bindEventAccessor(
        eventAccessorImpl: EventRepository
    ): EventAccessor
}