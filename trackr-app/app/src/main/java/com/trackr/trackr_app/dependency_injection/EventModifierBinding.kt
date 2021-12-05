package com.trackr.trackr_app.dependency_injection

import com.trackr.trackr_app.manager.EventManager
import com.trackr.trackr_app.manager.EventModifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class EventModifierBinding {
    @Binds
    abstract fun bindEventModifier(
        eventCreatorImpl: EventManager
    ): EventModifier
}