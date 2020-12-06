package utils

fun cleansePassword(data: String): List<String> {
    return data.split(" ").map { item -> item.trim().removeSuffix(":").split("-") }.flatten()
}

fun cleansePassportsToList(data: List<String>, delimeter: String): MutableList<String> {
    return data.joinToString().replace(",", "").split(delimeter).toMutableList()
}