package com.thiru.cleanarchisample.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.thiru.cleanarchisample.domain.model.User
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UserDao {

    @Query("SELECT * FROM User where email LIKE :email AND password LIKE :password")
    fun findByEmailAndPassword(email: String, password: String): Single<User>

    @Query("SELECT * FROM User where email LIKE  :email")
    fun findByEmail(email: String): Single<User>

    @Query("SELECT COUNT(*) from User")
    fun countUsers(): Observable<Int>

    @Insert
    fun insertAll(user: User):Maybe<Long>

    @Query("SELECT COUNT(*)  FROM User WHERE email = :emailId")
    fun isDataExist(emailId: String): Maybe<Int>
}