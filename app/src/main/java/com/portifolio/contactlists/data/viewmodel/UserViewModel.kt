package com.portifolio.contactlists.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.portifolio.contactlists.data.repository.UserRepository
import com.portifolio.contactlists.data.database.UserDatabase
import com.portifolio.contactlists.data.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<UserEntity>>
    private val repository: UserRepository

    fun getReadAllData() = readAllData


    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUSer(user)
        }
    }

    fun deleteUser(user: UserEntity){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteUser(user) }
    }

    fun deleteAllUsers(){
        viewModelScope.launch(Dispatchers.IO) { repository.deleteAllUsers() }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<UserEntity>>{
        return repository.searchDatabase(searchQuery).asLiveData()
    }


}