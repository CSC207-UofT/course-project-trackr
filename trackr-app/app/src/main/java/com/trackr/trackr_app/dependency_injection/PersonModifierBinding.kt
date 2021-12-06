package com.trackr.trackr_app.dependency_injection

import com.trackr.trackr_app.manager.PersonManager
import com.trackr.trackr_app.manager.PersonModifier
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/*
This abstract class is used by hilt.
Binds the PersonModifier interface to the PersonManager.
When EventModifier is injected into a constructor, this binding allows hilt to know that
PersonManager is the implementation of PersonModifier.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class PersonModifierBinding {
    @Binds
    abstract fun bindPersonModifier(
        personModifierImpl: PersonManager
    ): PersonModifier
}