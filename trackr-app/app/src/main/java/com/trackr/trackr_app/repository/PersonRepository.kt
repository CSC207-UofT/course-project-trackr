package com.trackr.trackr_app.repository

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow

class PersonRepository(private val personDao: PersonDao) {
    val allPersons: Flow<List<Person>> = personDao.listpersons()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(person: Person) {
        personDao.delete(person.id, person.user_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editFirstName(new_first_name: String, person: Person) {
        personDao.editFirstName(new_first_name, person.id, person.user_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun editLastName(new_last_name: String, person: Person) {
        personDao.editLastName(new_last_name, person.id, person.user_id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        personDao.deleteAll()
    }
}
