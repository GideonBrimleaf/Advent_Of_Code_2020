package utils

fun cleansePassword(data: String): List<String> {
    return data.split(" ").map { item -> item.trim().removeSuffix(":").split("-") }.flatten()
}