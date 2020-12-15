package day9

import com.github.shiguruikai.combinatoricskt.combinations

fun findOddOneOut(numbers: List<Long>, preamble: Int): Long? {
    var startPoint = preamble
    val targetNumbers = numbers.toList().subList(preamble, numbers.size)
    var oddOneOut:Long? = null

    targetNumbers.forEach { it ->
        val lookupRange = numbers.toList().subList(startPoint-preamble, startPoint).combinations(2)
        val lookupSums = lookupRange.map { it.sum() }

        if (!lookupSums.contains(it)) {
            oddOneOut = it
            return oddOneOut
        } else {
            startPoint += 1
        }
    }
    return oddOneOut
}

fun findOddOneOutComponents(numbers: List<Long>, targetNumber:Long): List<Long> {
    var contiguousRangeToSum = mutableListOf<Long>()

    for (startPoint in 0..numbers.size) {
        for (number in numbers.subList(startPoint, numbers.size)) {
            contiguousRangeToSum.add(number)
            if (contiguousRangeToSum.sum() == targetNumber) {
                return contiguousRangeToSum
            } else if (number > targetNumber) {
                contiguousRangeToSum = mutableListOf()
                break
            }
        }
    }

    return contiguousRangeToSum
}