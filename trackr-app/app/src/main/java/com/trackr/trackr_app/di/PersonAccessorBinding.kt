package com.trackr.trackr_app.di

import com.trackr.trackr_app.repository.PersonAccessor
import com.trackr.trackr_app.repository.PersonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
This abstract class is used by hilt.
Binds the PersonAccessor interface to the PersonRepository.
When PersonAccessor is injected into a constructor, this binding allows hilt to know that
PersonRepository is the implementation of PersonAccessor
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PersonAccessorBinding {
    @Binds
    abstract fun bindPersonAccessor(
        personAccessorImpl: PersonRepository
    ): PersonAccessor
}