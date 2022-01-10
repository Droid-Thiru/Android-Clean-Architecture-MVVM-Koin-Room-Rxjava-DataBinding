package com.thiru.cleanarchisample.presenters.dashboard

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.thiru.cleanarchisample.R
import com.thiru.cleanarchisample.databinding.ActivityDashboardBinding
import com.thiru.cleanarchisample.domain.model.User

class DashboardActivity : AppCompatActivity() {

    private val binding : ActivityDashboardBinding by lazy { DataBindingUtil.setContentView(this,R.layout.activity_dashboard) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        intent.extras?.getParcelable<User>("user")?.let {
            binding.user = it
        }
    }

    companion object{
        fun start(context: Context, user:User){
            val intent = Intent(context, DashboardActivity::class.java)
            intent.apply {
                putExtra("user",user)
            }
            context.startActivity(intent)
        }
    }
}