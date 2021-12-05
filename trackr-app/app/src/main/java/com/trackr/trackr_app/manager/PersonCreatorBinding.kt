package com.trackr.trackr_app.manager

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PersonCreatorBinding {
    @Binds
    abstract fun bindPersonCreator(
        personCreatorImpl: PersonManager
    ): PersonCreator
}