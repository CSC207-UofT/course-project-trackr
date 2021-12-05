package com.trackr.trackr_app.di

import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.PersonModifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PersonModifierBinding {
    @Binds
    abstract fun bindPersonModifier(
        personModifierImpl: PersonManager
    ): PersonModifier
}