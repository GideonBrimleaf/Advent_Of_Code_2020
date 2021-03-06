
import day1.ExpenseCalc
import day10.getDistinctCombinations
import day10.getJoltDifferences
import day10.joltListToMap
import day2.PasswordChecker
import day3.tobogganTraversal
import day4.convertPassportListToMap
import day4.countValidPassports
import day4.countValidPassportsAndValidData
import day5.SeatLocator
import day6.getDistinctAnswers
import day6.getGroupedAnswers
import day6.getSumOfAllYesAnswers
import day6.getSumOfDistinctAnswers
import day7.countValidExternalBags1
import day7.countValidInternalBags
import day7.toBagQuantityData
import day7.toTruthyBags
import day8.deLoop
import day8.infiniteLoopCounter
import day8.pairData
import day9.findOddOneOut
import day9.findOddOneOutComponents
import utils.*

fun main(args: Array<String>) {
    val expenseData = fileToList("./src/main/data/ExpenseCalc.txt").map { it.toInt() }

    val calculator = ExpenseCalc(expenseData)

    println("Day 1 Part 1 - " + calculator.calculate(2020, calculator.expenses))
    println("Day 1 Part 2 - " + calculator.calculateThrees(2020))

    val passwordData = fileToList("./src/main/data/Passwords.txt").map {
        PasswordChecker(cleansePassword(it)).isValidPart1()
    }

    println("Day 2 Part 1 - " + passwordData.filter { result -> result }.size)

    val passwordDataPt2 = fileToList("./src/main/data/Passwords.txt").map {
        PasswordChecker(cleansePassword(it)).isValidPart2()
    }

    println("Day 2 Part 2 - " + passwordDataPt2.filter { result -> result }.size)

    val tobogganTraversalPt1 = fileToList("./src/main/data/TreeMap.txt")

    println("Day 3 Part 1 - " + tobogganTraversal(tobogganTraversalPt1, 3, 1))

    val tobogganTraversalPt2 =
        tobogganTraversal(tobogganTraversalPt1, 1,1) *
            tobogganTraversal(tobogganTraversalPt1, 3,1) *
            tobogganTraversal(tobogganTraversalPt1, 5, 1) *
            tobogganTraversal(tobogganTraversalPt1, 7, 1) *
            tobogganTraversal(tobogganTraversalPt1, 1, 2).toLong()

    println("Day 3 Part 2 - $tobogganTraversalPt2")

    val cleansedData = readInMultiLineFile("./src/main/data/Passports.txt")
    val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map {
            word -> word.split(" ")
    }
    val mappedData = convertPassportListToMap(splitData)

    println("Day 4 Part 1 - " + countValidPassports(mappedData))
    println("Day 4 Part 2 - " + countValidPassportsAndValidData(mappedData))

    val seatData = fileToList("./src/main/data/Seats.txt").map {
        SeatLocator(it)
    }

    val planeColumns = IntArray(8) {it}.toMutableList()
    val planeRows = IntArray(128) {it}.toMutableList()

    val seatIds = seatData.map {
        val seatRow = it.calculatePosition("row", planeRows)
        val seatColumn = it.calculatePosition("column", planeColumns)
        it.getSeatId(seatRow, seatColumn)
    }

    println("Day 5 Part 1 - " + seatIds.maxOrNull())

    val allPossibleSeatIds = IntArray(seatIds.size - seatIds.minOrNull()!!) {it + seatIds.minOrNull()!!}.toMutableList()
    println("Day 5 Part 2 - " + allPossibleSeatIds.minus(seatIds).first())

    val cleansedQuestionnaireData = readInMultiLineFile("./src/main/data/Questionnaire.txt")
    val splitQuestionnaireData = cleanseQuestionnaireToList(cleansedQuestionnaireData, "NEWLINE!!")
    val distinctAnswers = getDistinctAnswers(splitQuestionnaireData)
    val groupedAnswers = getGroupedAnswers(cleansedQuestionnaireData)

    println("Day 6 Part 1 - " + getSumOfDistinctAnswers(distinctAnswers))
    println("Day 6 Part 2 - " + getSumOfAllYesAnswers(groupedAnswers, splitQuestionnaireData))

    val bags = fileToList("./src/main/data/BagList.txt")

    val bagQuantities = toBagQuantityData(bags)
    val truthyBags = toTruthyBags(bagQuantities)

    println("Day 7 Part 1 - " + countValidExternalBags1(truthyBags, bagQuantities))
    println("Day 7 Part 2 - " + countValidInternalBags("shiny gold", bagQuantities))

    val bootInstructions = fileToList("./src/main/data/Instructions.txt")

    val transformedBootInstructions = pairData(bootInstructions)
    println("Day 8 Part 1 - " + infiniteLoopCounter(transformedBootInstructions))

    val nonLoopingData = deLoop(transformedBootInstructions)
    println("Day 8 Part 2 - " + infiniteLoopCounter(nonLoopingData))

    val xmasCodeData =  fileToList("./src/main/data/XmasCode.txt").map { it.toLong() }
    val oddOneOut = findOddOneOut(xmasCodeData, 25)
    println("Day 9 Part 1 - $oddOneOut")

    val oddOneOutComponents = findOddOneOutComponents(xmasCodeData, oddOneOut!!)
    val totalForOddOneOutComponents = oddOneOutComponents.maxOrNull()?.plus(oddOneOutComponents.minOrNull()!!)
    println("Day 9 Part 2 - $totalForOddOneOutComponents")

    val jolts = fileToList("./src/main/data/Jolts.txt").map { it.toInt() }
    val joltDifferences = getJoltDifferences(jolts)

    println("Day 10 Part 1 - " + (joltDifferences[1]?.times(joltDifferences[3] ?: 0)))

    val mappedJolts = joltListToMap(jolts)
    println("Day 10 Part 2 - " + getDistinctCombinations(mappedJolts))

}