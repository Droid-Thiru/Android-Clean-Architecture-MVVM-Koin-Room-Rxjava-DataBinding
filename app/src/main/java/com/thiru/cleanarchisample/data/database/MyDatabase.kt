package com.thiru.cleanarchisample.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thiru.cleanarchisample.domain.model.User

const val MY_DATA_BASE = "my-database"

@Database(entities = [User::class], version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}