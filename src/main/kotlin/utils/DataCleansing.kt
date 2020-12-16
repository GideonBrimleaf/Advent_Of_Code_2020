package utils

import java.io.File

fun fileToMutableList(pathName: String): MutableList<String> {
    var resultList = mutableListOf<String>()
    File(pathName).forEachLine {
        resultList.add(it)
    }
    return resultList
}

fun cleansePassword(data: String): List<String> {
    return data.split(" ").map { item -> item.trim().removeSuffix(":").split("-") }.flatten()
}

fun readInMultiLineFile(pathName: String): MutableList<String> {
    val resultList = mutableListOf<String>()
    File(pathName).forEachLine {
        if (it == "") {
            resultList.add("NEWLINE!!")
        } else {
            resultList.add(it)
        }
    }
    return resultList
}

fun cleansePassportsToList(data: List<String>, delimiter: String): MutableList<String> {
    return data.joinToString().replace(",", "").split(delimiter).toMutableList()
}

fun cleanseQuestionnaireToList(data: List<String>, delimiter: String): MutableList<String> {
    return data.joinToString().replace(",", "").replace(" ", "").split(delimiter).toMutableList()
}