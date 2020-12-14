package day7

import kotlin.math.pow

fun toReferentialBags(bagList: List<String>): Map<String, String> {
    return bagList.map {
        it.split(" ")
    }.associate {
        it.subList(0,2).joinToString().replace(",", "") to it.subList(2, it.size).joinToString().replace(",", "")
    }
}

fun toTruthyBags(bagData: Map<String, String>): MutableMap<String, Boolean> {
    return bagData.mapValues {
        it.value.contains("shiny gold")
    }.toMutableMap()
}

fun toBagQuantityData(bagList: List<String>): Map<String, Map<String, Int?>> {
    return bagList.map {
        it.replace(".","")
            .replace(",", "")
    }.associate {
        it.substringBefore("contain")
            .replace("bags","")
            .trim() to
                it.substringAfter("contain")
                    .trim()
    }.mapValues {
        it.value.split(" bag ", " bags ", " bag")
    }.mapValues {
        it.value.subList(0, it.value.size-1)
    }.mapValues { it ->
        it.value.associateBy( {it.substringAfter(" ")}, {it.substringBefore(" ").toIntOrNull()})
    }.mapValues {
        if (it.value.containsKey("other")) {
            mutableMapOf<String, Int>()
        } else {
            it.value
        }
    }
}

fun toBagCount(bagQuantities: Map<String, Map<String, Int?>>): Map<String, Int> {
    return bagQuantities.entries.associate { it ->
        if (it.value.isNotEmpty()) {
            it.key to it.value.map { it.value }.sumOf { it!! }
        } else {
            it.key to 0
        }
    }
}

fun countValidExternalBags(truthyBagList: MutableMap<String,Boolean>, referentialBagList: Map<String, String>): Int {
    repeat(truthyBagList.size) {
        truthyBagList.forEach { (bagKey, associatedBags) ->
            if (associatedBags) {
                referentialBagList.forEach { (bag, bags) ->
                    if (bags.contains(bagKey)) {
                        truthyBagList[bag] = true
                    }
                }
            }
        }
    }
    return truthyBagList.count { it.value }
}

fun countValidInternalBags(
    startPoint : String,
    bagQuantities: Map<String, Int>,
    referentialBagList: Map<String, Map<String, Int?>>
): Int {
    return bagCounter2(startPoint, referentialBagList)
}

fun bagCounter2(
    startPoint : String,
    referentialBagList: Map<String, Map<String, Int?>>,
    parentBagQty: Int = 1
): Int {
    var runningTotal = 0
    referentialBagList[startPoint]!!.forEach { (bagType, bagQty) ->
        runningTotal += bagQty!! * parentBagQty
        if (referentialBagList[bagType]!!.isNotEmpty()) {
            runningTotal += bagCounter2(bagType, referentialBagList, bagQty * parentBagQty)
        }
    }
    return runningTotal
}