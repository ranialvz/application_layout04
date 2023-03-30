package com.ralvez.myapplicationlayout04

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ralvez.myapplicationlayout04.databinding.ActivityMainBinding
import com.ralvez.myapplicationlayout04.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private var Name:String?=null
    private var score:Int=0

    private var currentPosition:Int=1
    private var questionList:ArrayList<QuizData> ? = null
    private var selecedOption:Int=0

    private lateinit var countDownTimer: CountDownTimer
    private val startTimeInMillis: Long = 10000 // 60 seconds in milliseconds
    private var timeLeftInMillis: Long = startTimeInMillis


    private lateinit var binding: ActivityQuizBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding= ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)
        startTimer()
        updateTimer()




        Name=intent.getStringExtra(myData.name)

        questionList=myData.getQuestion()

        setQuestion()

        binding.opt1.setOnClickListener{

            selectedOptionStyle(binding.opt1,1)
        }
        binding.opt2.setOnClickListener{

            selectedOptionStyle(binding.opt2,2)
        }
        binding.opt3.setOnClickListener{

            selectedOptionStyle(binding.opt3,3)
        }
        binding.opt4.setOnClickListener{

            selectedOptionStyle(binding.opt4,4)
        }

        binding.btnSubmit.setOnClickListener {



            if(selecedOption!=0)
            {
                val question=questionList!![currentPosition-1]
                if(selecedOption!=question.correct_ans)
                {
                    setColor(selecedOption,R.drawable.wrong_option)
                }else{

                    score++;
                }
                setColor(question.correct_ans,R.drawable.correct_option)
                resetTimer()


                if(currentPosition==questionList!!.size)
                    binding.btnSubmit.text="FINISH"

                else
                    binding.btnSubmit.text="SUBMIT"


            }else{
                currentPosition++
                resetTimer()
                startTimer()
                when{
                    currentPosition<=questionList!!.size->{
                        setQuestion()
                    }
                    else->{
                        resetTimer()
                        var intent= Intent(this,FinishActivity::class.java)

                        intent.putExtra(myData.name,Name.toString())
                        intent.putExtra(myData.score,score.toString())
                        intent.putExtra("total size",questionList!!.size.toString())
                        startActivity(intent)
                        finish()

                    }
                }
            }
            selecedOption=0
        }

    }

    fun setColor(opt:Int,color:Int){
        when(opt){
            1->{
                binding.opt1.background=ContextCompat.getDrawable(this,color)
            }
            2->{
                binding.opt2.background= ContextCompat.getDrawable(this,color)
            }
            3->{
                binding.opt3.background=ContextCompat.getDrawable(this,color)
            }
            4->{
                binding.opt4.background=ContextCompat.getDrawable(this,color)
            }
        }
    }


    fun setQuestion(){

        val question = questionList!![currentPosition-1]
        setOptionStyle()

        binding.progressText.text="${currentPosition}"+"/"+"${questionList!!.size}"
        binding.questionText.text=question.question
        binding.opt1.text=question.option_one
        binding.opt2.text=question.option_tw0
        binding.opt3.text=question.option_three
        binding.opt4.text=question.option_four

    }

    fun setOptionStyle(){
        var optionList:ArrayList<TextView> = arrayListOf()
        optionList.add(0,binding.opt1)
        optionList.add(1,binding.opt2)
        optionList.add(2,binding.opt3)
        optionList.add(3,binding.opt4)

        for(op in optionList)
        {
            op.setTextColor(Color.parseColor("#555151"))
            op.background=ContextCompat.getDrawable(this,R.drawable.question_option)
            op.typeface= Typeface.DEFAULT
        }
    }

    fun selectedOptionStyle(view:TextView,opt:Int){

        setOptionStyle()
        selecedOption=opt
        view.background=ContextCompat.getDrawable(this,R.drawable.select_option)
        view.typeface= Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#000000"))

    }



    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMillis = millisUntilFinished

                updateTimer()
            }

            override fun onFinish() {
                timeLeftInMillis = 0

                updateTimer()
            }
        }.start()


    }

    private fun resetTimer() {
        countDownTimer.cancel()
        timeLeftInMillis = startTimeInMillis
        updateTimer()

    }

    private fun updateTimer() {
        val seconds = (timeLeftInMillis / 1000).toString()
        if (seconds== "0"){
            val toast = Toast.makeText(this, "Time is Up!", Toast.LENGTH_SHORT)
            toast.show()
            currentPosition++
            resetTimer()
            startTimer()
            when{
                currentPosition<=questionList!!.size->{
                    setQuestion()
                }
                else->{
                    resetTimer()
                    var intent= Intent(this,FinishActivity::class.java)

                    intent.putExtra(myData.name,Name.toString())
                    intent.putExtra(myData.score,score.toString())
                    intent.putExtra("total size",questionList!!.size.toString())
                    startActivity(intent)
                    finish()

                }
            }
        }
        binding.tvCountdown.text=seconds

    }


}