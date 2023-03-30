package com.ralvez.myapplicationlayout04

object myData {

    const val name:String="name"
    const val score:String="score"

    fun getQuestion():ArrayList<QuizData>{
        var que:ArrayList<QuizData> = arrayListOf()

        var question1 = QuizData(
            1,
            "What is capital of Philipines ?",

            "Laguna",
            "Cebu",
            "Davao",
            "Manila",
            4
        )
        var question2 = QuizData(
            2,
            "Who is the current president of the philippines ?",

            "Bongbong Marcos",
            "Rodrigo Duterte",
            "Manny Paquiao",
            "Erap Estrada",
            1
        )
        var question3 = QuizData(
            3,
            "What is the philippine National Anthem ?",

            "Bayang Magiliw",
            "Lupang Hinirang",
            "Huling el Bimbo",
            "lakas tama",
            2
        )
        var question4 = QuizData(
            4,
            "Who is the national hero of the philippines ?",

            "Rodrego Duterte",
            "Andres Bonifacio",
            "Dr. Josi Rizal ",
            "Joe Biden",
            3
        )

        var question5 = QuizData(
            5,
            "Who kill Magellan ?",

            "Paquiao",
            "Trillianes",
            "De lima",
            "Lapulapu",
            4
        )

        que.add(question1)
        que.add(question2)
        que.add(question3)
        que.add(question4)
        que.add(question5)
        return que
    }
}