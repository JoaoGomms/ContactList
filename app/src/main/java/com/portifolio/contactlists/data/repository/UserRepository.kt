package com.portifolio.contactlists.data.repository

import androidx.lifecycle.LiveData
import com.portifolio.contactlists.data.dao.UserDao
import com.portifolio.contactlists.data.model.UserEntity

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserEntity>> = userDao.readAllData()

    suspend fun addUser(user: UserEntity){
        userDao.addUser(user)
    }

    suspend fun updateUSer(user: UserEntity){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserEntity){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }



}