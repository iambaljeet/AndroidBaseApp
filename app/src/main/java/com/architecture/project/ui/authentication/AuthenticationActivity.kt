package com.architecture.project.ui.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.architecture.project.R

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var viewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
        viewModel = ViewModelProvider(this).get(AuthenticationViewModel::class.java)
    }

    companion object {
        fun launchActivity(activity: Activity) {
            val authenticationIntent = Intent(activity, AuthenticationActivity::class.java)
            activity.startActivity(authenticationIntent)
        }
    }
}