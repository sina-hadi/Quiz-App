package com.codinginflow.letsgo

object Constants {

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()
        val titleQue = "What country does this flag belong to?"

        val que1 = Question(
            1, titleQue, R.drawable.ic_flag_of_argentina,
            "Argentina", "Australia",
            "Armenia", "Austria", 1
        )
        val que2 = Question(
            1, titleQue, R.drawable.ic_flag_of_belgium,
            "Bahamas", "Belgium",
            "Barbados", "Belize", 2
        )
        val que3 = Question(
            1, titleQue, R.drawable.ic_flag_of_brazil,
            "Belarus", "Belize",
            "Brunei", "Brazil", 4
        )
        val que4 = Question(
            1, titleQue, R.drawable.ic_flag_of_australia,
            "Australia", "New Zealand",
            "Tuvalu", "America", 1
        )
        val que5 = Question(
            1, titleQue, R.drawable.ic_flag_of_denmark,
            "Dominica", "Egypt",
            "Denmark", "Ethiopia", 3
        )
        val que6 = Question(
            1, titleQue, R.drawable.ic_flag_of_fiji,
            "Gabon", "Austria",
            "Fiji", "Finland", 3
        )
        val que7 = Question(
            1, titleQue, R.drawable.ic_flag_of_germany,
            "Germany", "Georgia",
            "Greece", "None", 1
        )
        val que8 = Question(
            1, titleQue, R.drawable.ic_flag_of_india,
            "Ireland", "Iran",
            "Hungary", "India", 4
        )
        val que9 = Question(
            1, titleQue, R.drawable.ic_flag_of_kuwait,
            "Kuwait", "Jordan",
            "Sudan", "Palestine", 1
        )
        val que10 = Question(
            1, titleQue, R.drawable.ic_flag_of_new_zealand,
            "Australia", "New Zealand",
            "Tuvalu", "America", 2
        )
        questionList.add(que1)
        questionList.add(que2)
        questionList.add(que3)
        questionList.add(que4)
        questionList.add(que5)
        questionList.add(que6)
        questionList.add(que7)
        questionList.add(que8)
        questionList.add(que9)
        questionList.add(que10)
        return questionList
    }
}