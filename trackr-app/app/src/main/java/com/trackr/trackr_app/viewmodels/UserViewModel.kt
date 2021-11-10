package com.trackr.trackr_app.viewmodels

import androidx.lifecycle.*
import com.trackr.trackr_app.repository.UserRepository
import com.trackr.trackr_app.model.User
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun updateUsername(new_username: String, user: User) = viewModelScope.launch {
        repository.updateUsername(new_username, user)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}