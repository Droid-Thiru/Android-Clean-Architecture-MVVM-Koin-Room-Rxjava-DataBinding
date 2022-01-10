package com.thiru.cleanarchisample.utils

import org.koin.core.component.KoinComponent

class FormValidation : KoinComponent {
    
    private val emailPattern  = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    
    fun isValidEmail(username:String):Pair<Boolean,String> = when {
        username.isEmpty() -> {
            Pair(false, "Required.")
        }
        !username.matches(emailPattern.toRegex()) -> {
            Pair(false, "Enter valid email id.")
        }
        else -> {
            Pair(true,"")
        }
    }

    fun isValidPassword(password:String):Pair<Boolean,String> = when{
        password.isEmpty() -> {
            Pair(false, "Required.")
        }
        password.length<6 -> {
            Pair(false, "Enter valid password.")
        }
        else -> {
            Pair(true,"")
        }
    }

    fun isValidConfirmPassword(password:String,confirmPassword:String):Pair<Boolean,String> = when{
        confirmPassword.isEmpty() -> {
            Pair(false, "Required.")
        }
        confirmPassword != password -> {
            Pair(false, "Password mismatch.")
        }
        else -> {
            Pair(true,"")
        }
    }

    fun isValidUsername(name:String):Pair<Boolean,String> = when{
        name.isEmpty() -> {
            Pair(false, "Required.")
        }
        name.length<3 -> {
            Pair(false, "Minimum require 3 letters.")
        }
        else -> {
            Pair(true,"")
        }
    }
}