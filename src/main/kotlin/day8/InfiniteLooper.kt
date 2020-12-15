package day8

import java.lang.Exception

fun pairData(rawInstructions: List<String>): List<Pair<Int, String>> {
    return rawInstructions.map {
        it.substringAfter(" ").toInt() to it.substring(0,3)
    }
}

fun deLoop(loopingData: List<Pair<Int, String>>): List<Pair<Int, String>> {
    var deLoopingData = loopingData.toMutableList()
    val changeRef = mapOf("nop" to "jmp", "jmp" to "nop", "acc" to "acc")

    loopingData.forEachIndexed { index, pair ->
        deLoopingData[index] = pair.first to (changeRef[pair.second] ?: pair.second)
        if (!infiniteLoopCounter(deLoopingData).second)
            return deLoopingData
        else deLoopingData[index] = pair.first to pair.second
    }

    throw Exception("Cannot remove infinite loop!")
}

fun infiniteLoopCounter(loopingData: List<Pair<Int, String>>):Pair<Int, Boolean> {
    var runningAccumulator = 0
    var currentPointer = 0
    val visitedPointers = mutableListOf<Int>()
    var infiniteLoop = true

    while (!visitedPointers.contains(currentPointer)) {
        visitedPointers.add(currentPointer)

        runningAccumulator += if(loopingData[currentPointer].second == "acc")
            loopingData[currentPointer].first
        else 0

        currentPointer += if (loopingData[currentPointer].second == "jmp")
            loopingData[currentPointer].first
        else 1

        if (currentPointer > loopingData.lastIndex) {
            infiniteLoop = false
            break
        }
    }

    return runningAccumulator to infiniteLoop
}