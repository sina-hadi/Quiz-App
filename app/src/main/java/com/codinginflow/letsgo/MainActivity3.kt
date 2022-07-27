package com.codinginflow.letsgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main3.*

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val myPoint = intent.getStringExtra("key")
        val myName = intent.getStringExtra("myName")

        textView.setText("${myPoint.toString()} / 10")
        textView2.setText("Congratulations $myName")




    }
}