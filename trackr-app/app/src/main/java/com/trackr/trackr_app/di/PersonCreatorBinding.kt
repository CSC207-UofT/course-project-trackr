package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.PersonCreator
import com.trackr.trackr_app.manager.PersonManager
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