import day2.PasswordChecker
import day1.ExpenseCalc
import day3.tobogganTraversal
import day4.convertPassportListToMap
import day4.countValidPassports
import day4.countValidPassportsAndValidData
import utils.cleansePassportsToList
import java.io.File
import utils.cleansePassword
import utils.readInPassportFile

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
    readInPassportFile("./src/main/data/Passports.txt", cleansedData)
    val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
    val mappedData = convertPassportListToMap(splitData)

    println("Day 4 Part 1 - " + countValidPassports(mappedData))
    println("Day 4 Part 2 - " + countValidPassportsAndValidData(mappedData))



}