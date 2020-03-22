package com.architecture.project.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.architecture.project.R
import com.architecture.project.ui.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AuthenticationActivity.launchActivity(this)
        finish()

//        helloWorldTextView.setOnClickListener {
//            val dialog = DialogWithTwoButtons.newInstance("test", "test message", "Yes", "No")
//            dialog?.show(supportFragmentManager, dialog.tag)
//        }
    }
}