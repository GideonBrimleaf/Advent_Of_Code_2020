package day4

fun convertPassportListToMap(listData: List<List<String>>): List<Map<String, String>> {
    return listData.map { item ->
        item.associate { it.substringBefore(":") to it.substringAfter(":") }
    }
}

fun countValidPassports(passportData : List<Map<String, String>>): Int {
    return passportData.count { containsValidKeys(it) }
}

fun countValidPassportsAndValidData(passportData : List<Map<String, String>>): Int {
    return passportData.count { containsValidKeys(it) && containsValidIntData(it)
                && containsValidHeightData(it) && containsValidPassportId(it)
                && containsValidHairColour(it) && containsValidEyeColour(it)
    }
}

fun containsValidKeys(passport : Map<String, String>): Boolean {
    return passport.containsKey("byr") && passport.containsKey("eyr")
            && passport.containsKey("iyr") && passport.containsKey("hgt")
            && passport.containsKey("hcl") && passport.containsKey("ecl") && passport.containsKey("pid")
}

fun containsValidIntData(passport: Map<String, String>): Boolean {
    return if (containsValidKeys(passport)) {
        passport["byr"]!!.toInt() in 1920..2002 && passport["iyr"]!!.toInt() in 2010..2020
                && passport["eyr"]!!.toInt() in 2020..2030
    } else false
}

fun containsValidHeightData(passport: Map<String, String>): Boolean {
    return if (containsValidKeys(passport)) {
        (passport["hgt"]!!.contains("cm") && passport["hgt"]!!.split("cm").first().toInt() in 150..193)
                || (passport["hgt"]!!.contains("in") && passport["hgt"]!!.split("in").first().toInt() in 59..76)
    } else false
}

fun containsValidPassportId(passport: Map<String, String>): Boolean {
    return if (containsValidKeys(passport)) {
        passport["pid"]!!.length == 9 && passport["pid"]!!.toIntOrNull() != null
    } else false
}

fun containsValidHairColour(passport: Map<String, String>): Boolean {
    return if (containsValidKeys(passport)) {
        passport["hcl"]!!.startsWith('#') && passport["hcl"]!!.length == 7
                && (
                passport["hcl"]!!.substringAfter('#').contains("[0-9]".toRegex())
                        || passport["hcl"]!!.substringAfter('#').contains("[a-f]".toRegex())
                )
                && !passport["hcl"]!!.substringAfter('#').contains("[g-z]".toRegex())
    } else false
}

fun containsValidEyeColour(passport: Map<String, String>): Boolean {
    return if (containsValidKeys(passport)) {
        passport["ecl"]!!.matches("amb|blu|brn|gry|grn|hzl|oth".toRegex())
    } else false
}