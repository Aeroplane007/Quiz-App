package com.example.quizzapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val quiz_list: MutableList<RecyclerViewItem>, private val OnQuizClickListener: RecyclerViewInterface) : RecyclerView.Adapter<RecyclerViewAdapter.QuizViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quiz_item, parent, false)
        return QuizViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return quiz_list.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val currenItem = quiz_list[position]
        holder.quiz_name.text = currenItem.quiz_name

        holder.itemView.setOnClickListener {
            OnQuizClickListener.OnItemClick(position)
        }
    }

    class QuizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val quiz_name : TextView = itemView.findViewById(R.id.quiz_name)

    }

}