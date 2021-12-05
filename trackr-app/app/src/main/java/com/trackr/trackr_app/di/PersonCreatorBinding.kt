package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.PersonCreator
import com.trackr.trackr_app.manager.PersonManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
This abstract class is used by hilt.
Binds the PersonCreator interface to the PersonRepository.
When PersonCreator is injected into a constructor, this binding allows hilt to know that
PersonRepository is the implementation of PersonCreator
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PersonCreatorBinding {
    @Binds
    abstract fun bindPersonCreator(
        personCreatorImpl: PersonManager
    ): PersonCreator
}