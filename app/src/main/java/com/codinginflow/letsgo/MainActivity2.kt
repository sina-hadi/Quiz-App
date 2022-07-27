package com.codinginflow.letsgo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() , View.OnClickListener{

    private var mCurrentPosition:Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0
    private var correctAnswer = 0
    private var isSubmit : Boolean = true
    private var point = 0
    private var isResult = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val actionbar = supportActionBar
        actionbar!!.title = "New Activity"
//        actionbar.setDisplayHomeAsUpEnabled(true)
//        actionbar.setDisplayHomeAsUpEnabled(true)

        val myName = intent.getStringExtra("myName")

        mQuestionList = Constants.getQuestions()

        setQuestion()

        tv_option_one.setOnClickListener { if (isSubmit == true) {onClick(tv_option_one)} }
        tv_option_two.setOnClickListener { if (isSubmit == true) {onClick(tv_option_two)} }
        tv_option_three.setOnClickListener { if (isSubmit == true) {onClick(tv_option_three)} }
        tv_option_four.setOnClickListener { if (isSubmit == true) {onClick(tv_option_four)} }

        btn_submit.setOnClickListener {
            if (isResult == true){
                val intent = Intent(this,MainActivity3::class.java)
                intent.putExtra("key",point.toString())
                intent.putExtra("myName",myName)
                startActivity(intent)

            }

            else if (mSelectedOptionPosition != 0) {
                if (mCurrentPosition == 10){
                    onClick(btn_submit)
                    isSubmit = false
                    btn_submit.setText("Result")
                    isResult = true
                }else if (isSubmit == false) {
                    mCurrentPosition++
                    isSubmit = true
                    btn_submit.setText("Submit")
                    mSelectedOptionPosition = 0
                    setQuestion()
                } else {
                    onClick(btn_submit)
                    isSubmit = false
                    btn_submit.setText("Go to Next")
                }
            }else{
                Toast.makeText(this,"Choose your option",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setQuestion(){


        val question = mQuestionList!![mCurrentPosition-1]

        defaultOptionView()

        progressbar.progress = mCurrentPosition
//        tv_progress.text = "$mCurrentPosition/${progressbar.max}"
        tv_quesion.text = question!!.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
        correctAnswer = question.correctAnswer
    }


    private fun defaultOptionView(){
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this,R.drawable.default_option_border_bg)
        }

    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.tv_option_one -> {selectedOptionView(tv_option_one, 1)}
            R.id.tv_option_two -> {selectedOptionView(tv_option_two, 2)}
            R.id.tv_option_three -> {selectedOptionView(tv_option_three, 3)}
            R.id.tv_option_four -> {selectedOptionView(tv_option_four, 4)}
            R.id.btn_submit -> {answerOptionView(mSelectedOptionPosition,correctAnswer)}
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this,R.drawable.selected_option_border_bg)
    }

    private fun answerOptionView(mSelected: Int,correctAnswer2: Int) {
        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        if (mSelected != correctAnswer2) {

            val option1 = options[mSelected - 1]

            option1.setTextColor(Color.parseColor("#FFFFFF"))
            option1.typeface = Typeface.DEFAULT
            option1.background =
                ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

            val option2 = options[correctAnswer2 - 1]

            option2.setTextColor(Color.parseColor("#FFFFFF"))
            option2.typeface = Typeface.DEFAULT
            option2.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)



        }else{
            val option2 = options[correctAnswer2 - 1]

            point ++

            tv_progress.setText("$point / 10")

            option2.setTextColor(Color.parseColor("#FFFFFF"))
            option2.typeface = Typeface.DEFAULT
            option2.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
        }
    }
}