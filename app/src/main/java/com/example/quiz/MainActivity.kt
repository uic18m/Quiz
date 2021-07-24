package com.example.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar : ProgressBar
    private lateinit var tv_progress : TextView
    private lateinit var iv_image: ImageView
    private lateinit var option_one: TextView
    private lateinit var option_two: TextView
    private lateinit var option_three: TextView
    private lateinit var option_four: TextView
    private lateinit var btn_submit : Button

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectionOptionPostion: Int = 0
    private var mCorrectAnswers: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        option_one=findViewById(R.id.option_one)
        option_two=findViewById(R.id.option_two)
        option_three=findViewById(R.id.option_three)
        option_four=findViewById(R.id.option_four)
        btn_submit=findViewById(R.id.btn_submit)
        progressBar=findViewById(R.id.progressBar)
        tv_progress=findViewById(R.id.tv_progress)
        iv_image=findViewById(R.id.iv_image)


        mQuestionsList = Constants.getQuestions()

        setQuestion()

        option_one.setOnClickListener(this)
        option_two.setOnClickListener(this)
        option_three.setOnClickListener(this)
        option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {
        val question= mQuestionsList!![mCurrentPosition-1]
        defaultOptionsView()

        if(mCurrentPosition==mQuestionsList!!.size){
            btn_submit.text="FINISH"
        }else{
            btn_submit.text="SUBMIT"
        }
        progressBar.progress=mCurrentPosition
        tv_progress.text="$mCurrentPosition"+"/"+progressBar.max
        //tv_progress.text= question.question

        iv_image.setImageResource(question.image)
        option_one.text=question.optionOne
        option_two.text=question.optionTwo
        option_three.text=question.optionThree
        option_four.text=question.optionFour
    }

    private fun defaultOptionsView() {
        val options=ArrayList<TextView>()

        options.add(0,option_one)
        options.add(1,option_two)
        options.add(2,option_three)
        options.add(3,option_four)
        for(option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface= Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.option_one->{
                selectedOptionView(option_one,1)
            }
            R.id.option_two->{
                selectedOptionView(option_two,2)
            }
            R.id.option_three->{
                selectedOptionView(option_three,3)
            }
            R.id.option_four->{
                selectedOptionView(option_four,4)
            }
            R.id.btn_submit->{
                if(mSelectionOptionPostion==0){
                    mCurrentPosition++
                    when{
                        mCurrentPosition<=mQuestionsList!!.size->{
                            setQuestion()

                        }else->{
                        Toast.makeText(this,"You have successfully completed the Quiz", Toast.LENGTH_SHORT).show()
                         }
                    }
                }else{
                    val question=mQuestionsList?.get(mCurrentPosition-1)
                    if(question!!.correctAnswer!=mSelectionOptionPostion){
                        answerView(mSelectionOptionPostion,R.drawable.wrong_option_border_bg)
                    }
                    else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer,R.drawable.correct_option_border_bg)
                    if(mCurrentPosition==mQuestionsList!!.size){
                        btn_submit.text="FINISH"

                    }else{
                        btn_submit.text="GO TO NEXT QUESTION"
                    }
                    mSelectionOptionPostion =0
                }

            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer){
            1->{
                option_one.background=ContextCompat.getDrawable(this,drawableView)
            }
            2->{
                option_two.background=ContextCompat.getDrawable(this,drawableView)
            }
            3->{
                option_three.background=ContextCompat.getDrawable(this,drawableView)
            }
            4->{
                option_four.background=ContextCompat.getDrawable(this,drawableView)
            }
        }

    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectionOptionPostion=selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background= ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg)

    }


}