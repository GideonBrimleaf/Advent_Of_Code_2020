package utils

fun cleansePassword(data: String): List<String> {
    return data.split(" ").map { item -> item.trim().removeSuffix(":").split("-") }.flatten()
}

fun cleansePassportsToList(data: List<String>, delimeter: String): MutableList<String> {
    return data.joinToString().replace(",", "").split(delimeter).toMutableList()
}

fun convertPassportListToMap(listData: List<List<String>>, mapToFill: MutableList<Map<String, String>>) {
    listData.forEach {
        val mapValue = it.associate { it.substringBefore(":") to it.substringAfter(":") }
        mapToFill.add(mapValue)
    }
}