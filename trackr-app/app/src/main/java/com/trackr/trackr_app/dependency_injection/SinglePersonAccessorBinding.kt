package com.trackr.trackr_app.dependency_injection

import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.SinglePersonAccessor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SinglePersonAccessorBinding {
    @Binds
    abstract fun bindSinglePersonAccessor(
        singlePersonAccessorBindingImpl: PersonManager
    ): SinglePersonAccessor
}