package day10

fun getAdapterRating(jolts: List<Int>): Int {
    return (jolts.maxOrNull() ?: 0) + 3
}

fun getJoltDifferences(jolts: List<Int>): Map<Int,Int> {
    val result = mutableMapOf(1 to 0, 3 to 0)
    val extendedJolts = jolts.toMutableList()
    extendedJolts.addAll(listOf(0, getAdapterRating(jolts)))
    val sortedJolts = extendedJolts.sorted()

    return sortedJolts.foldIndexed(result) {index, acc, jolt ->
        if (index+1 <= sortedJolts.lastIndex)
            if (sortedJolts[index + 1]-jolt == 1)
                acc[1] = acc[1]!! + 1
            else if(sortedJolts[index + 1]-jolt == 3)
                acc[3] = acc[3]!! + 1
        acc
    }
}

fun joltListToMap(joltList: List<Int>): Map<Int, List<Int>> {
    val adapters = joltList.toMutableList()
    adapters.addAll(listOf(0, getAdapterRating(joltList)))
    val sortedAdapters = adapters.sorted()

    val result = mutableMapOf<Int, List<Int>>()
    sortedAdapters.forEachIndexed { index, jolt ->
        result[jolt] = sortedAdapters.subList(index + 1, sortedAdapters.size).filter { it - jolt <= 3 }
    }
    return result
}

fun getDistinctCombinations(joltMap: Map<Int, List<Int>>): Map<String, Long> {
    val children = joltMap[0]!!
    val result = mutableMapOf("count" to 0.toLong())

    children.forEach {
        countDistinctCombos(it, joltMap, result)
    }
    return result
}

fun countDistinctCombos(key: Int, joltMap: Map<Int, List<Int>>, result: MutableMap<String, Long>) {
    val children = joltMap[key]!!
    if (children.isEmpty()) {
        println("Hit the end of the node!" + (result["count"]!! + 1))
        result["count"] = result["count"]!! + 1
        return
    }

    var index = 0
    repeat(children.size) {
        val child = children[index]
        index += 1
        countDistinctCombos(child, joltMap, result)
    }
}
