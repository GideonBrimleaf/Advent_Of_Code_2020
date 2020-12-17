package day6

import utils.Breakdownable

fun getGroupedAnswers(questionnaire: List<String>): List<List<String>> {
    return questionnaire.joinToString().split(" NEWLINE!!").map {
        it.removePrefix(", ").removeSuffix(",").split(",")
    }
}

fun getDistinctAnswers(questionnaire: List<String>): List<String> {
    return questionnaire.map {
        it.split("").distinct().joinToString().replace(",", "").replace(" ", "")
    }
}

fun getSumOfDistinctAnswers(distinctQuestionnaireAnswers: List<String>): Int {
    return distinctQuestionnaireAnswers.fold(0, {acc, answers ->
        acc + answers.count()
    })
}

fun getAllYesAnswers(groupedQuestionnaireData: List<String>, condensedQuestionnaireData: String): Int {
    val peopleInGroup = groupedQuestionnaireData.size
    val questionCount = Breakdownable.getBreakdown(condensedQuestionnaireData)

    val allYes = questionCount.filter { (key, value) ->
        value == peopleInGroup
    }
    return allYes.size
}

fun getSumOfAllYesAnswers(questionnaireDataByGroup: List<List<String>>, condensedQuestionnaireDataList: List<String>): Int {

    return questionnaireDataByGroup.foldIndexed(0) { index, total, item ->
        total + getAllYesAnswers(item, condensedQuestionnaireDataList[index])
    }
}