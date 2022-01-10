package com.thiru.cleanarchisample.domain.usecase

import com.thiru.cleanarchisample.domain.model.User
import com.thiru.cleanarchisample.domain.repository.UserRepository
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterUseCase(private val repository: UserRepository) {
    fun registration(user: User): Maybe<Long> {
        return repository.isDataExist(user.email)
            .flatMap{
                if(it==0){
                    return@flatMap repository.insertUser(user)
                }else{
                    return@flatMap Maybe.error(Throwable("'${user.email}' is already exist. Please try another User."))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}