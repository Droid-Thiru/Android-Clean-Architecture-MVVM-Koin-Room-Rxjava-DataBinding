package com.thiru.cleanarchisample.domain.repository

import com.thiru.cleanarchisample.domain.model.User
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

interface UserRepository {
    fun getUserFindByEmailAndPassword(email: String, password: String):Single<User>
    fun getUserFindByEmail(email: String):Single<User>
    fun getUsersCount():Observable<Int>
    fun insertUser(user: User): Maybe<Long>
    fun isDataExist(emailID: String): Maybe<Int>
}