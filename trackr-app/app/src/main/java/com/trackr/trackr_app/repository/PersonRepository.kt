package com.trackr.trackr_app.repository

import androidx.annotation.WorkerThread
import com.trackr.trackr_app.database.PersonDao
import com.trackr.trackr_app.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PersonRepository @Inject constructor(
    private val personDao: PersonDao
    ) {
    val allPersons: Flow<List<Person>> = personDao.listPersons()

    @WorkerThread
    suspend fun getPersonById(id: String): Person {
        return personDao.getPersonById(id)
    }

    @WorkerThread
    suspend fun getPersonByName(firstName: String, lastName: String): Person {
        return personDao.getPersonByName(firstName, lastName)
    }

    @WorkerThread
    suspend fun hasPersonByName(firstName: String, lastName: String): Boolean {
        return personDao.hasPersonByName(firstName, lastName)
    }

    @WorkerThread
    suspend fun insert(person: Person) {
        personDao.insert(person)
    }

    @WorkerThread
    suspend fun delete(person: Person) {
        personDao.delete(person.id, person.userId)
    }

    @WorkerThread
    suspend fun editFirstName(new_first_name: String, person: Person) {
        personDao.editFirstName(new_first_name, person.id, person.userId)
    }

    @WorkerThread
    suspend fun editLastName(new_last_name: String, person: Person) {
        personDao.editLastName(new_last_name, person.id, person.userId)
    }

    @WorkerThread
    suspend fun deleteAll() {
        personDao.deleteAll()
    }
}
