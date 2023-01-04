package com.codinginflow.letsgo

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.codinginflow.letsgo.databinding.ActivityFinishBinding

class FinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFinishBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "End"

        val myPoint = intent.getStringExtra("key")
        val myName = intent.getStringExtra("myName")

        binding.textView.text = "${myPoint.toString()} / 10"
        binding.textView2.text = "Congratulations $myName"

        binding.btnRestart.setOnClickListener {
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onBackPressed() {
        Log.e("BackPressed", "TRUE")
    }
}