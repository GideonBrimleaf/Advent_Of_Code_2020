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