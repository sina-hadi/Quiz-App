package com.codinginflow.letsgo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        findViewById<ImageView>(R.id.splash_image).alpha = 0f
        findViewById<ImageView>(R.id.splash_image).animate().setDuration(1500).alpha(1f)
            .withEndAction {
                Thread.sleep(500L)
                val intent = Intent(this, LogInActivity::class.java)
                startActivity(intent)
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            }
    }
}