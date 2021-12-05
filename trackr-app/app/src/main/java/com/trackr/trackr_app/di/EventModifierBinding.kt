package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.EventManager
import com.trackr.trackr_app.manager.EventModifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/*
This abstract class is used by hilt.
Binds the EventModifier interface to the EventManager.
When EventModifier is injected into a constructor, this binding allows hilt to know that
EventManager is the implementation of EventModifier.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class EventModifierBinding {
    @Binds
    abstract fun bindEventModifier(
        eventCreatorImpl: EventManager
    ): EventModifier
}