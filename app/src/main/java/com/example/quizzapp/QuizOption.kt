package com.example.quizzapp

import androidx.room.util.copy

class QuizOption() {
    lateinit var name: String
    lateinit var image : String

    constructor(name: String) : this() {
        this.name = name
        this.image = getImageName()
    }

    fun getImageName() : String{
        var temp = name.toCharArray()
        for(i in 0 until name.length){
            if(temp[i] == ' '){
                temp[i] = '_'
            }
            else if(name[i].isUpperCase()){
                temp[i] = temp[i].lowercaseChar()
            }
        }
        return String(temp)
    }
}