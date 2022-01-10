package com.thiru.cleanarchisample.presenters.registration

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.thiru.cleanarchisample.base.BaseViewModel
import com.thiru.cleanarchisample.domain.model.User
import com.thiru.cleanarchisample.domain.usecase.RegisterUseCase
import com.thiru.cleanarchisample.utils.FormValidation

class RegistrationViewModel(private val registrationUseCase: RegisterUseCase, private val formValidation: FormValidation) : BaseViewModel(){
    private val TAG = "RegistrationViewModel"
    val user = User()

    var isValidEmail = false
    var isValidFirstName = false
    var isValidLastName = false
    var isValidPassword = false
    var isValidConfirmPassword = false

    private var emailValidationPair:Pair<Boolean,String>? = null
    private var firstNameValidationPair:Pair<Boolean,String>? = null
    private var lastNameValidationPair:Pair<Boolean,String>? = null
    private var passwordValidationPair:Pair<Boolean,String>? = null
    private var confirmPasswordValidationPair:Pair<Boolean,String>? = null

    private val emailErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val emailErrorLiveData: LiveData<String> by lazy { emailErrorLive }

    private val firstNameErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val firstNameErrorLiveData: LiveData<String> by lazy { firstNameErrorLive }

    private val lastNameErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val lastNameErrorLiveData: LiveData<String> by lazy { lastNameErrorLive }

    private val passwordErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val passwordErrorLiveData: LiveData<String> by lazy { passwordErrorLive }

    private val confirmPasswordErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val confirmPasswordErrorLiveData: LiveData<String> by lazy { confirmPasswordErrorLive }

    private val registerEnableLive:MutableLiveData<Boolean> by lazy { MutableLiveData() }
    val registerEnableLiveData: LiveData<Boolean> by lazy { registerEnableLive }

    private val registerLive:MutableLiveData<Long> by lazy { MutableLiveData() }
    val registerLiveData: LiveData<Long> by lazy { registerLive }

    private val registerErrorLive:MutableLiveData<String> by lazy { MutableLiveData() }
    val registerErrorLiveData: LiveData<String> by lazy { registerErrorLive }

    fun onEmailTextChange(text:CharSequence){
        emailValidationPair = formValidation.isValidEmail(text.toString().trim())
        isValidEmail = emailValidationPair?.first?:false
        emailErrorLive.value = emailValidationPair?.second
        enableRegistration()
    }

    fun onFirstNameTextChange(text:CharSequence){
        firstNameValidationPair = formValidation.isValidUsername(text.toString().trim())
        isValidFirstName = firstNameValidationPair?.first?:false
        firstNameErrorLive.value = firstNameValidationPair?.second
        enableRegistration()
    }

    fun onLastNameTextChange(text:CharSequence){
        lastNameValidationPair = formValidation.isValidUsername(text.toString().trim())
        isValidLastName = lastNameValidationPair?.first?:false
        lastNameErrorLive.value = lastNameValidationPair?.second
        enableRegistration()
    }

    fun onPasswordTextChange(text:CharSequence){
        passwordValidationPair = formValidation.isValidPassword(text.toString().trim())
        isValidPassword = passwordValidationPair?.first?:false
        passwordErrorLive.value = passwordValidationPair?.second
        enableRegistration()
    }

    fun onConfirmPasswordTextChange(text:CharSequence){
        confirmPasswordValidationPair = formValidation.isValidConfirmPassword(user.password,text.toString().trim())
        isValidConfirmPassword = confirmPasswordValidationPair?.first?:false
        confirmPasswordErrorLive.value = confirmPasswordValidationPair?.second
        enableRegistration()
    }

    private fun enableRegistration(){
        registerEnableLive.value = isValidEmail && isValidFirstName && isValidLastName
                && isValidPassword && isValidConfirmPassword
    }

    fun onRegister(){
        compositeDisposable.add(registrationUseCase.registration(user)
            .subscribe({
                registerLive.value = it
            },{
                registerErrorLive.value = it.message
            }))
    }
}