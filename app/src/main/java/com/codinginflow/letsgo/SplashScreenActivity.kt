package com.codinginflow.letsgo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.codinginflow.letsgo.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, LogInActivity::class.java)

        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        binding.splashImage.startAnimation(animationFadeIn)
        animationFadeIn.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.splashImage.startAnimation(animationFadeOut)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        animationFadeOut.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.splashImage.visibility = View.INVISIBLE
                startActivity(intent)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })

    }
}