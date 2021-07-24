package com.example.quiz

import java.util.*

object Constants{
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"
    fun getQuestions(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()
        //1
        val que1=Question(1,"What is name of this animal? ",
            R.drawable.camel,
            "Tiger",
            "Lion",
            "Camel",
            "Dog",
            3
        )
        questionsList.add(que1)
        //2
        val que2=Question(2,"What is the name of this animal? ",
            R.drawable.tiger,
            "Tiger",
            "Lion",
            "Camel",
            "Dog",
            1

        )
        questionsList.add(que2)

        //3
        val que3=Question(3,"What is the name of this animal? ",
            R.drawable.dear,
            "Tiger",
            "Dear",
            "Elephant",
            "Camel",
            2

        )
        questionsList.add(que3)

        //4
        val que4=Question(4,"What is the name of this animal? ",
            R.drawable.elephant,
            "Tiger",
            "Lion",
            "Camel",
            "Elephant",
            4

        )
        questionsList.add(que4)

        //5
        val que5=Question(5,"What is the name of this animal? ",
            R.drawable.peacokok,
            "Tiger",
            "Peacokok",
            "Elephant",
            "Camel",
            2

        )
        questionsList.add(que5)
        return questionsList
    }
}