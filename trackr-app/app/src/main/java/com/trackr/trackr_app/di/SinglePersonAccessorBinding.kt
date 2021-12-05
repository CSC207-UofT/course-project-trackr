package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.SinglePersonAccessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
This abstract class is used by hilt.
Binds the SinglePersonAccessor interface to the PersonManager.
When SinglePersonAccessor is injected into a constructor, this binding allows hilt to know that
PersonManager is the implementation of SinglePersonAccessor.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class SinglePersonAccessorBinding {
    @Binds
    abstract fun bindSinglePersonAccessor(
        singlePersonAccessorBindingImpl: PersonManager
    ): SinglePersonAccessor
}