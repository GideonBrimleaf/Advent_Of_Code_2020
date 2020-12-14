import day2.PasswordChecker
import day1.ExpenseCalc
import day3.tobogganTraversal
import day4.convertPassportListToMap
import day4.countValidPassports
import day4.countValidPassportsAndValidData
import day5.SeatLocator
import day6.getDistinctAnswers
import day6.getGroupedAnswers
import day6.getSumOfAllYesAnswers
import day6.getSumOfDistinctAnswers
import day7.*
import utils.cleansePassportsToList
import java.io.File
import utils.cleansePassword
import utils.cleanseQuestionnaireToList
import utils.readInMultiLineFile

fun main(args: Array<String>) {
    val expenseData = mutableListOf<Int>()

    File("./src/main/data/ExpenseCalc.txt").forEachLine {
        expenseData.add(it.toInt())
    }

    val calculator = ExpenseCalc(expenseData)

    println("Day 1 Part 1 - " + calculator.calculate(2020, calculator.expenses))
    println("Day 1 Part 2 - " + calculator.calculateThrees(2020))

    val passwordData = mutableListOf<Boolean>()

    File("./src/main/data/Passwords.txt").forEachLine {
        passwordData.add(PasswordChecker(cleansePassword(it)).isValidPart1())
    }

    println("Day 2 Part 1 - " + passwordData.filter { result -> result }.size)

    val passwordDataPt2 = mutableListOf<Boolean>()

    File("./src/main/data/Passwords.txt").forEachLine {
        passwordDataPt2.add(PasswordChecker(cleansePassword(it)).isValidPart2())
    }

    println("Day 2 Part 2 - " + passwordDataPt2.filter { result -> result }.size)

    val tobogganTraversalPt1 = mutableListOf<String>()

    File("./src/main/data/TreeMap.txt").forEachLine {
        tobogganTraversalPt1.add(it)
    }

    println("Day 3 Part 1 - " + tobogganTraversal(tobogganTraversalPt1, 3, 1))

    val tobogganTraversalPt2 : Long =
        tobogganTraversal(tobogganTraversalPt1, 1,1) *
            tobogganTraversal(tobogganTraversalPt1, 3,1) *
            tobogganTraversal(tobogganTraversalPt1, 5, 1) *
            tobogganTraversal(tobogganTraversalPt1, 7, 1) *
            tobogganTraversal(tobogganTraversalPt1, 1, 2).toLong()

    println("Day 3 Part 2 - $tobogganTraversalPt2")

    var cleansedData = mutableListOf<String>()
    readInMultiLineFile("./src/main/data/Passports.txt", cleansedData)
    val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
    val mappedData = convertPassportListToMap(splitData)

    println("Day 4 Part 1 - " + countValidPassports(mappedData))
    println("Day 4 Part 2 - " + countValidPassportsAndValidData(mappedData))

    var seatData = mutableListOf<SeatLocator>()
    var seatIds = mutableListOf<Int>()
    val planeColumns = IntArray(8) {it}.toMutableList()
    val planeRows = IntArray(128) {it}.toMutableList()


    File("./src/main/data/Seats.txt").forEachLine {
        seatData.add(SeatLocator(it))
    }

    seatData.forEach {
        val seatRow = it.calculatePosition("row", planeRows)
        val seatColumn = it.calculatePosition("column", planeColumns)
        seatIds.add(it.getSeatId(seatRow, seatColumn))
    }

    println("Day 5 Part 1 - " + seatIds.maxOrNull())

    val allPossibleSeatIds = IntArray(seatIds.size - seatIds.minOrNull()!!) {it + seatIds.minOrNull()!!}.toMutableList()
    println("Day 5 Part 2 - " + allPossibleSeatIds.minus(seatIds).first())


    var cleansedQuestionnaireData = mutableListOf<String>()
    readInMultiLineFile("./src/main/data/Questionnaire.txt", cleansedQuestionnaireData)
    val splitQuestionnaireData = cleanseQuestionnaireToList(cleansedQuestionnaireData, "NEWLINE!!")
    val distinctAnswers = getDistinctAnswers(splitQuestionnaireData)
    val groupedAnswers = getGroupedAnswers(cleansedQuestionnaireData)

    println("Day 6 Part 1 - " + getSumOfDistinctAnswers(distinctAnswers))
    println("Day 6 Part 2 - " + getSumOfAllYesAnswers(groupedAnswers, splitQuestionnaireData))

    val bags = mutableListOf<String>()

    File("./src/main/data/BagList.txt").forEachLine {
        bags.add(it)
    }

    val referentialBags = toReferentialBags(bags)
    val bagQuantities = toBagQuantityData(bags)
    val truthyBags = toTruthyBags(bagQuantities)



    println("Day 7 Part 1 - " + countValidExternalBags2(truthyBags, bagQuantities))
    println("Day 7 Part 2 - " + countValidInternalBags("shiny gold", bagQuantities))

}