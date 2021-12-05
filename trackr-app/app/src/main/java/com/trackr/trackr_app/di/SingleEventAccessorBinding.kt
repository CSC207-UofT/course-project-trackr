package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.EventManager
import com.trackr.trackr_app.manager.SingleEventAccessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
/*
This abstract class is used by hilt.
Binds the SingleEventAccessor interface to the EventManager.
When SingleEventAccessor is injected into a constructor, this binding allows hilt to know that
EventManager is the implementation of SingleEventAccessor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SingleEventAccessorBinding {
    @Binds
    abstract fun bindSingleEventAccessor(
        eventCreatorImpl: EventManager
    ): SingleEventAccessor
}