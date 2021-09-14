package com.portifolio.contactlists.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.portifolio.contactlists.data.model.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun readAllData(): LiveData<List<UserEntity>>

    @Update
    suspend fun updateUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM user")
    suspend fun deleteAllUsers()

}