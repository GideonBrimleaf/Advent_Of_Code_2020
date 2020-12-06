package day4

fun convertPassportListToMap(listData: List<List<String>>): List<Map<String, String>> {
    return listData.map { item ->
        item.associate { it.substringBefore(":") to it.substringAfter(":") }
    }
}

fun countValidPassports(passportData : List<Map<String, String>>): Int {
    return passportData.count {
        it.containsKey("byr") && it.contains("iyr") && it.containsKey("eyr")
                && it.containsKey("hgt") && it.containsKey("hcl")
                && it.containsKey("ecl") && it.containsKey("pid")
    }
}