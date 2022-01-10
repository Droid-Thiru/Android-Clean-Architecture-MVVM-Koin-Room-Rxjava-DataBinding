package com.thiru.cleanarchisample.presenters.login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thiru.cleanarchisample.R
import com.thiru.cleanarchisample.databinding.ActivityLoginBinding
import com.thiru.cleanarchisample.presenters.dashboard.DashboardActivity
import com.thiru.cleanarchisample.presenters.registration.RegistrationActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val binding : ActivityLoginBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    private val viewModel:LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)
        binding.loginViewModel = viewModel

        binding.signUpBtn.setOnClickListener {
            RegistrationActivity.start(this)
        }

        viewModel.emailErrorLiveData.observe(this){
            binding.usernameLayout.error = it
        }

        viewModel.passwordErrorLiveData.observe(this){
            binding.passwordLayout.error = it
        }

        viewModel.enableLoginLiveData.observe(this){
            binding.loginBtn.isEnabled = it
        }

        viewModel.loginLiveData.observe(this){
            Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()
            DashboardActivity.start(this,it)
            finish()
        }

        viewModel.loginErrorLiveData.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }
}