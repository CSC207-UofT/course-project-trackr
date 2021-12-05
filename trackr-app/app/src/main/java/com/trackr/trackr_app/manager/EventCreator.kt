package com.trackr.trackr_app.manager

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventCreator {
    @Binds
    abstract fun bindEventCreator(
        eventCreatorImpl: EventManager
    ): EventCreatorInterface
}