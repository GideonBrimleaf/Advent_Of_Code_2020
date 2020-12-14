package day7

fun toTruthyBags(bagData: Map<String, Map<String, Int?>>): MutableMap<String, Boolean> {
    return bagData.mapValues {
        it.value.keys.contains("shiny gold")
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

fun countValidExternalBags1(
    truthyBagList: MutableMap<String,Boolean>,
    referentialBagList: Map<String, Map<String, Int?>>
): Int {
    repeat(truthyBagList.size) {
        truthyBagList.forEach { (bagKey, associatedBags) ->
            if (associatedBags) {
                referentialBagList.forEach { (bag, bags) ->
                    if (bags.keys.contains(bagKey)) {
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
    referentialBagList: Map<String, Map<String, Int?>>,
    parentBagQty: Int = 1
): Int {
    var runningTotal = 0
    referentialBagList[startPoint]!!.forEach { (bagType, bagQty) ->
        runningTotal += bagQty!! * parentBagQty
        if (referentialBagList[bagType]!!.isNotEmpty()) {
            runningTotal += countValidInternalBags(bagType, referentialBagList, bagQty * parentBagQty)
        }
    }
    return runningTotal
}