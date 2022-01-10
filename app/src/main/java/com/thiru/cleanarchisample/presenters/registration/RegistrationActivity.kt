package com.thiru.cleanarchisample.presenters.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.thiru.cleanarchisample.R
import com.thiru.cleanarchisample.databinding.ActivityRegistrationBinding
import org.koin.android.ext.android.inject

class RegistrationActivity : AppCompatActivity() {

    private val binding: ActivityRegistrationBinding by lazy {
        DataBindingUtil.setContentView(
            this,
            R.layout.activity_registration
        )
    }
    private val viewModel: RegistrationViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registrationViewModel = viewModel
        binding.user = viewModel.user

        initObserver()
    }

    private fun initObserver() {
        viewModel.emailErrorLiveData.observe(this) {
            binding.emailLayout.error = it
        }

        viewModel.firstNameErrorLiveData.observe(this) {
            binding.firstNameLayout.error = it
        }

        viewModel.lastNameErrorLiveData.observe(this) {
            binding.lastNameLayout.error = it
        }

        viewModel.passwordErrorLiveData.observe(this) {
            binding.passwordLayout.error = it
        }

        viewModel.confirmPasswordErrorLiveData.observe(this) {
            binding.confirmPasswordLayout.error = it
        }

        viewModel.registerEnableLiveData.observe(this) {
            binding.registerBtn.isEnabled = it
        }

        viewModel.registerLiveData.observe(this){
            finish()
        }

        viewModel.registerErrorLiveData.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    companion object{
        fun start(context:Context){
            val intent = Intent(context, RegistrationActivity::class.java)
            context.startActivity(intent)
        }
    }
}