package com.thiru.cleanarchisample.data.repository

import com.thiru.cleanarchisample.data.database.UserDao
import com.thiru.cleanarchisample.domain.model.User
import com.thiru.cleanarchisample.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository{
    override fun getUserFindByEmailAndPassword(email: String, password: String): Single<User> {
        return userDao.findByEmailAndPassword(email,password)
    }

    override fun getUserFindByEmail(email: String): Single<User> {
        return userDao.findByEmail(email)
    }

    override fun getUsersCount(): Observable<Int> {
        return userDao.countUsers()
    }

    override fun insertUser(user: User): Maybe<Long> {
        return userDao.insertAll(user)
    }

    override fun isDataExist(emailID: String): Maybe<Int> {
        return userDao.isDataExist(emailID)
    }
}