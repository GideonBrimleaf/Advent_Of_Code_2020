import day2.PasswordChecker
import day1.ExpenseCalc
import java.io.File
import utils.cleansePassword

fun main(args: Array<String>) {
    val expenseData = mutableListOf<Int>()

    File("./src/main/data/ExpenseCalc.txt").forEachLine {
        expenseData.add(it.toInt())
    }

    val calculator = ExpenseCalc(expenseData)

    println(calculator.calculate(2020, calculator.expenses))
    println(calculator.calculateThrees(2020))

    val passwordData = mutableListOf<Boolean>()

    File("./src/main/data/Passwords.txt").forEachLine {
        passwordData.add(PasswordChecker(cleansePassword(it)).isValidPart1())
    }

    println(passwordData.filter { result -> result }.size)

    val passwordDataPt2 = mutableListOf<Boolean>()

    File("./src/main/data/Passwords.txt").forEachLine {
        passwordDataPt2.add(PasswordChecker(cleansePassword(it)).isValidPart2())
    }

    println(passwordDataPt2.filter { result -> result }.size)

}