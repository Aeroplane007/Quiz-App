package com.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), RecyclerViewInterface{

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var list_array : ArrayList<RecyclerViewItem>
    private lateinit var item_name : Array<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initilizeQuizNames()
        setpuRecyclerView(R.id.quiz_list)
        populateRecyclerView()
    }

    private fun populateRecyclerView(){

        list_array = arrayListOf<RecyclerViewItem>()

        for(i in item_name.indices){
            val quiz = RecyclerViewItem(item_name[i])
            list_array.add(quiz)
        }

        newRecyclerView.adapter = RecyclerViewAdapter(list_array, this)

    }

    private fun initilizeQuizNames(){
        item_name = arrayOf(
            resources.getString(R.string.countries),
            resources.getString(R.string.cats)

        )
    }

    private fun setpuRecyclerView(activity_ref: Int){
        newRecyclerView = findViewById(activity_ref)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)
    }


    override fun OnItemClick(position: Int) {

        val intent = Intent(applicationContext, Quiz::class.java)
        intent.putExtra("QUIZ_TYPE", item_name[position])
        startActivity(intent)

    }
}




