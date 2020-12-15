package day8

fun pairData(rawInstructions: List<String>): List<Pair<String, Int>> {
    return rawInstructions.map {
        it.substring(0,3) to it.substringAfter(" ").toInt()
    }
}

fun infiniteLoopCounter(loopingData: List<Pair<String, Int>>):Int {
    var runningAccumulator = 0
    var currentPointer = 0
    val visitedPointers = mutableListOf<Int>()

    while (!visitedPointers.contains(currentPointer)) {
        visitedPointers.add(currentPointer)

        runningAccumulator += if(loopingData[currentPointer].first == "acc")
            loopingData[currentPointer].second
        else 0

        currentPointer += if (loopingData[currentPointer].first == "jmp")
            loopingData[currentPointer].second
        else 1
    }

    return runningAccumulator
}