package com.portifolio.contactlists.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.portifolio.contactlists.data.Converters
import com.portifolio.contactlists.data.dao.UserDao
import com.portifolio.contactlists.data.model.UserEntity

@Database(entities = [UserEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                //FIXME: Refactor .fallbackToDestructiveMigrationOnDowngrade() to Migration
                ).fallbackToDestructiveMigrationOnDowngrade().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}