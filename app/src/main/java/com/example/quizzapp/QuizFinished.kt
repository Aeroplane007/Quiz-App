package com.example.quizzapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizFinished(): AppCompatActivity() {

    private lateinit var score_text : TextView
    private var points : Int = 0
    private var number_of_questions : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz_finished_view)

        points = intent.getIntExtra("POINTS", 0)
        number_of_questions = intent.getIntExtra("NUMBER_OF_QUESTIONS", 0)

        score_text = findViewById(R.id.ScoreText)
        score_text.text = "Score: " + points.toString() + "/" + number_of_questions.toString()
    }

    fun continueHome(v: View){
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }
}