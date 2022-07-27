package com.codinginflow.letsgo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btn_start.setOnClickListener {
            if (et_name.text.toString().isEmpty()) {
                Toast.makeText(this,"Enter Name !",Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this,MainActivity2::class.java)
                val myname = et_name.text.toString()
                intent.putExtra("myName",myname)
                startActivity(intent)
            }
        }



    }
}