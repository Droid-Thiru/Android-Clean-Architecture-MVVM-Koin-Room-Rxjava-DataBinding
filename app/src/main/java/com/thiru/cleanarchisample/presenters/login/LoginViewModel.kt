package com.thiru.cleanarchisample.presenters.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thiru.cleanarchisample.base.BaseViewModel
import com.thiru.cleanarchisample.domain.model.User
import com.thiru.cleanarchisample.domain.usecase.LoginUseCase
import com.thiru.cleanarchisample.utils.FormValidation

class LoginViewModel(private val loginUseCase: LoginUseCase, private val formValidation:FormValidation): BaseViewModel() {

    private var email = ""
    private var password = ""

    var isValidEmail = false
    var isValidPassword = false

    private var emailValidationPair: Pair<Boolean,String>? = null
    private var passwordValidationPair: Pair<Boolean,String>? = null

    private val emailErrorLive : MutableLiveData<String> by lazy { MutableLiveData() }
    val emailErrorLiveData : LiveData<String> by lazy { emailErrorLive }

    private val passwordErrorLive : MutableLiveData<String> by lazy { MutableLiveData() }
    val passwordErrorLiveData : LiveData<String> by lazy { passwordErrorLive }

    private val enableLoginLive : MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val enableLoginLiveData : LiveData<Boolean> by lazy { enableLoginLive }

    private val loginLive : MutableLiveData<User> by lazy { MutableLiveData() }
    val loginLiveData : LiveData<User> by lazy { loginLive }

    private val loginErrorLive : MutableLiveData<String> by lazy { MutableLiveData() }
    val loginErrorLiveData : LiveData<String> by lazy { loginErrorLive }

    fun onLogin(){
        if(isValidEmail && isValidPassword){
            compositeDisposable.add(loginUseCase.getUserFindByEmailAndPassword(email,password)
                .subscribe({
                    loginLive.value = it
                },{
                    loginErrorLive.value = it.message
                }))
        }
    }

    fun onEmailTextChange(text:CharSequence){
        email = text.toString()
        emailValidationPair = formValidation.isValidEmail(email.trim())
        isValidEmail = emailValidationPair?.first?:false
        emailErrorLive.value = emailValidationPair?.second
        enableLogin()
    }

    fun onPasswordTextChange(text:CharSequence){
        password = text.toString()
        passwordValidationPair = formValidation.isValidPassword(password.trim())
        isValidPassword = passwordValidationPair?.first?:false
        passwordErrorLive.value = passwordValidationPair?.second
        enableLogin()
    }

    private fun enableLogin(){
        enableLoginLive.value = isValidEmail && isValidPassword
    }
}