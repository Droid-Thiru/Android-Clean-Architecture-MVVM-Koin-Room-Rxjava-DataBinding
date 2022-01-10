package com.thiru.cleanarchisample.domain.usecase

import com.thiru.cleanarchisample.domain.model.User
import com.thiru.cleanarchisample.domain.repository.UserRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginUseCase(private val repository: UserRepository) {
    fun getUserFindByEmailAndPassword(email:String, password:String): Single<User> {
       val passwordErrorMsg = "Invalid Password."
        return repository.getUserFindByEmail(email)
            .flatMap {
                if(it.password != password){
                    return@flatMap Single.error(Throwable(passwordErrorMsg))
                }else{
                    return@flatMap Single.just(it)
                }
            }
            .onErrorResumeNext{
                if(it.message == passwordErrorMsg) {
                    return@onErrorResumeNext Single.error(it)
                }else{
                    return@onErrorResumeNext Single.error(Throwable("Invalid User. Register First."))
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}