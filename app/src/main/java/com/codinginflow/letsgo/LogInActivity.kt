package com.codinginflow.letsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.codinginflow.letsgo.databinding.ActivityLogInBinding
import kotlin.system.exitProcess

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Quiz App"

        binding.btnStart.setOnClickListener {

            if (binding.etName.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter Name!", Toast.LENGTH_SHORT).show()

            } else {
                val intent = Intent(this, QuizActivity::class.java)
                val myName = binding.etName.text.toString()
                intent.putExtra("myName", myName)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        finishAffinity()
//        exitProcess(0)
    }
}