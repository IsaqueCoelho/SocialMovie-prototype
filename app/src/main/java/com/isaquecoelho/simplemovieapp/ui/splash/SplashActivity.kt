package com.isaquecoelho.simplemovieapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.isaquecoelho.simplemovieapp.R
import com.isaquecoelho.simplemovieapp.ui.signIn.SignInActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        loadingData()
    }

    private fun loadingData() {
        Handler().postDelayed({
            startActivity( Intent(this, SignInActivity::class.java) )
            finish()
        }, 1500)
    }
}
