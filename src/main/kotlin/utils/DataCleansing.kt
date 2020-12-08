package utils

import java.io.File

fun cleansePassword(data: String): List<String> {
    return data.split(" ").map { item -> item.trim().removeSuffix(":").split("-") }.flatten()
}

fun readInMultiLineFile(pathName: String, resultList: MutableList<String>) {
    File(pathName).forEachLine {
        if (it == "") {
            resultList.add("NEWLINE!!")
        } else {
            resultList.add(it)
        }
    }
}

fun cleansePassportsToList(data: List<String>, delimeter: String): MutableList<String> {
    return data.joinToString().replace(",", "").split(delimeter).toMutableList()
}

fun cleanseQuestionnaireToList(data: List<String>, delimeter: String): MutableList<String> {
    return data.joinToString().replace(",", "").replace(" ", "").split(delimeter).toMutableList()
}