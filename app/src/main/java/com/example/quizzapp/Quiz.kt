package com.example.quizzapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.math.log


class Quiz : AppCompatActivity(){
    //consts
    val NUMBER_OF_QUESTIONS = 5
    val DATA = mapOf("Countries" to arrayOf("Brazil","Sweden","USA","Nigeria","France"),
                     "Cats" to arrayOf("Chinchilla persian", "Siberian", "British shorthair", "Burmese", "Bengal")
                     )

    private var clicked : Boolean  = false
    private lateinit var quiz_type : String
    private lateinit var quiz_image : ImageView
    private lateinit var quiz_button1 : Button
    private lateinit var quiz_button2 : Button
    private lateinit var quiz_button3 : Button
    private lateinit var quiz_button4 : Button
    private lateinit var next_button : Button
    private lateinit var quiz_data : MutableList<Array<QuizOption>>
    private var points : Int = 0
    private var question = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("wwesd", "debug1")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_view)

        quiz_type = intent.getStringExtra("QUIZ_TYPE") ?: ""


        quiz_image = findViewById(R.id.QuizImage)
        quiz_button1 = findViewById(R.id.QuizButton1)
        quiz_button2 = findViewById(R.id.QuizButton2)
        quiz_button3 = findViewById(R.id.QuizButton3)
        quiz_button4 = findViewById(R.id.QuizButton4)
        next_button = findViewById(R.id.NextButton)


        quiz_data = getQuizData()
        loadQuizData()


    }



    private fun getQuizData() : MutableList<Array<QuizOption>>{
        var numbers_to_pick = (0..4).random()
        val correct_option = mutableListOf<Int>()
        val data = mutableListOf<Array<QuizOption>>()


        //Put correct option in correct_list
        correct_option.add(numbers_to_pick)
        for(i in 1..4) {
            while(correct_option.contains(numbers_to_pick)){
                numbers_to_pick = (0..4).random()
            }
            correct_option.add(numbers_to_pick)
        }

        Log.d("test", DATA[quiz_type]?.get(0) ?: "none")

        for(d in correct_option){
            var button_options = arrayOf(
                QuizOption(DATA[this.quiz_type]?.get(d) ?: ""),
                QuizOption(DATA[this.quiz_type]?.get((d+1) % 4) ?: ""),
                QuizOption(DATA[this.quiz_type]?.get((d+2) % 4) ?: ""),
                QuizOption(DATA[this.quiz_type]?.get((d+3) % 4) ?: "")
            )
            data.add(button_options)
        }

        return data

    }

    //load data by puttong the image to the first item and randomizing the buttons value
    private fun loadQuizData(){
        val question_data = quiz_data[question]
        Log.d("wwesd", "image: "+ question_data[0].image + ", name: " + question_data[0].name)
        val image_resource = resources.getIdentifier("@drawable/"+question_data[0].image,
            null, packageName)
        quiz_image.setImageResource(image_resource)


        val random_question_list = question_data.copyOf()
        random_question_list.shuffle()


        quiz_button1.text = random_question_list[0].name
        quiz_button2.text = random_question_list[1].name
        quiz_button3.text = random_question_list[2].name
        quiz_button4.text = random_question_list[3].name

    }


    fun getCorrectButton(correct_option : String): Button{
        when(correct_option){
            (quiz_button1.text.toString()) -> {
                return quiz_button1
            }
            (quiz_button2.text.toString()) -> {
                return quiz_button2
            }
            (quiz_button3.text.toString()) -> {
                return quiz_button3
            }
            (quiz_button4.text.toString()) -> {
                return quiz_button4
            }
        }

        return quiz_button2
    }

    //onClick
    fun clickedQuizOption(v : View){
        if(clicked){
            return
        }
        clicked = true
        val clicked_button : Button
        val correct_option = quiz_data[question][0].name

        when(v.id){
            R.id.QuizButton1 -> {
                clicked_button = quiz_button1
            }
            R.id.QuizButton2 -> {
                clicked_button = quiz_button2
            }
            R.id.QuizButton3 -> {
                clicked_button = quiz_button3
            }
            R.id.QuizButton4 -> {
                clicked_button = quiz_button4
            }
            else -> {
                clicked_button = quiz_button1
            }
        }


        if(clicked_button.text.toString() == correct_option){
            //correct
            clicked_button.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            points += 1
        }else{
            //wrong
            clicked_button.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            getCorrectButton(correct_option).setBackgroundColor(ContextCompat.getColor(this, R.color.green))
        }

        next_button.visibility = View.VISIBLE

    }

    fun resetButtons(){
        quiz_button1.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color))
        quiz_button2.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color))
        quiz_button3.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color))
        quiz_button4.setBackgroundColor(ContextCompat.getColor(this, R.color.default_button_color))
    }

    fun nextQuestion(v : View){
        next_button.visibility = View.INVISIBLE
        Log.d("what", question.toString())
        if(question < (NUMBER_OF_QUESTIONS-1)){
            Log.d("what", "doin this")
            resetButtons()
            question += 1
            loadQuizData()
            clicked = false
        }else{
            Log.d("what", "doin that")
            val intent = Intent(applicationContext, QuizFinished::class.java)
            intent.putExtra("POINTS", points)
            intent.putExtra("NUMBER_OF_QUESTIONS", NUMBER_OF_QUESTIONS)
            startActivity(intent)
        }

    }
}