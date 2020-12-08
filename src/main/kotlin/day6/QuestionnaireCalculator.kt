package day6

fun getDistinctAnswers(questionnaire: MutableList<String>): List<String> {
    return questionnaire.map {
        it.split("").distinct().joinToString().replace(",", "").replace(" ", "")
    }
}

fun getSumOfDistinctAnswers(distinctQuestionnaireAnswers: List<String>): Int {
    return distinctQuestionnaireAnswers.fold(0, {acc, answers ->
        acc + answers.count()
    })
}