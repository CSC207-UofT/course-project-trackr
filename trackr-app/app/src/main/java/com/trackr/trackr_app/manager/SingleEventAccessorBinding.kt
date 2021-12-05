package com.trackr.trackr_app.manager

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SingleEventAccessorBinding {
    @Binds
    abstract fun bindSingleEventAccessor(
        eventCreatorImpl: EventManager
    ): SingleEventAccessor
}