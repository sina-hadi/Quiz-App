package com.codinginflow.letsgo

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.codinginflow.letsgo.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var correctAnswer = 0
    private var isSubmit: Boolean = true
    private var point = 0
    private var isResult = false

    private lateinit var binding: ActivityQuizBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.title = "Questions"
        actionbar.setDisplayHomeAsUpEnabled(true)

        val myName = intent.getStringExtra("myName")

        mQuestionList = Constants.getQuestions()

        setQuestion()

        binding.tvOptionOne.setOnClickListener {
            if (isSubmit) {
                onClick(binding.tvOptionOne)
            }
        }
        binding.tvOptionTwo.setOnClickListener {
            if (isSubmit) {
                onClick(binding.tvOptionTwo)
            }
        }
        binding.tvOptionThree.setOnClickListener {
            if (isSubmit) {
                onClick(binding.tvOptionThree)
            }
        }
        binding.tvOptionFour.setOnClickListener {
            if (isSubmit) {
                onClick(binding.tvOptionFour)
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (isResult) {
                val intent = Intent(this, FinishActivity::class.java)
                intent.putExtra("key", point.toString())
                intent.putExtra("myName", myName)
                startActivity(intent)
            } else if (mSelectedOptionPosition != 0) {
                if (mCurrentPosition == 10) {
                    onClick(binding.btnSubmit)
                    isSubmit = false
                    binding.btnSubmit.text = "Result"
                    isResult = true
                } else if (!isSubmit) {
                    mCurrentPosition++
                    isSubmit = true
                    binding.btnSubmit.text = "Submit"
                    mSelectedOptionPosition = 0
                    animationQuestion()
                } else {
                    onClick(binding.btnSubmit)
                    isSubmit = false
                    binding.btnSubmit.text = "Go to Next"
                }
            } else {
                Toast.makeText(this, "Choose your option", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setQuestion(){
        val question = mQuestionList!![mCurrentPosition - 1]
        binding.progressbar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition / 10"
        binding.tvQuestion.text = question.question
        binding.ivImage.setImageResource(question.image)
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour
        correctAnswer = question.correctAnswer
    }

    private fun animationQuestion() {
        val animationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        binding.linearlayout.startAnimation(animationFadeOut)
        animationFadeOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                enableDisableButton(false)
            }

            override fun onAnimationEnd(p0: Animation?) {
                defaultOptionView()
                setQuestion()
                binding.linearlayout.startAnimation(animationFadeIn)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        animationFadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                enableDisableButton(true)
            }

            override fun onAnimationRepeat(p0: Animation?) {}
        })
    }

    private fun enableDisableButton(state : Boolean) {
        binding.btnSubmit.isEnabled = state
        binding.btnSubmit.isClickable = state
        binding.tvOptionFour.isEnabled = state
        binding.tvOptionFour.isClickable = state
        binding.tvOptionThree.isEnabled = state
        binding.tvOptionThree.isClickable = state
        binding.tvOptionTwo.isEnabled = state
        binding.tvOptionTwo.isClickable = state
        binding.tvOptionOne.isEnabled = state
        binding.tvOptionOne.isClickable = state
    }


    private fun defaultOptionView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(binding.tvOptionOne, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(binding.tvOptionTwo, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(binding.tvOptionThree, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(binding.tvOptionFour, 4)
            }
            R.id.btn_submit -> {
                answerOptionView(mSelectedOptionPosition, correctAnswer)
            }
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionView()
        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#000000"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_bg)
    }

    private fun answerOptionView(mSelected: Int, correctAnswer2: Int) {
        val options = ArrayList<TextView>()
        options.add(0, binding.tvOptionOne)
        options.add(1, binding.tvOptionTwo)
        options.add(2, binding.tvOptionThree)
        options.add(3, binding.tvOptionFour)

        if (mSelected != correctAnswer2) {

            val option1 = options[mSelected - 1]

            option1.setTextColor(Color.parseColor("#FFFFFF"))
            option1.typeface = Typeface.DEFAULT
            option1.background = ContextCompat.getDrawable(this, R.drawable.wrong_option_border_bg)

            val option2 = options[correctAnswer2 - 1]

            option2.setTextColor(Color.parseColor("#FFFFFF"))
            option2.typeface = Typeface.DEFAULT
            option2.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)


        } else {
            val option2 = options[correctAnswer2 - 1]

            point++

            option2.setTextColor(Color.parseColor("#FFFFFF"))
            option2.typeface = Typeface.DEFAULT
            option2.background =
                ContextCompat.getDrawable(this, R.drawable.correct_option_border_bg)
        }
    }
}