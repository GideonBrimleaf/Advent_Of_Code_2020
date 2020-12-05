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
        passwordData.add(PasswordChecker(cleansePassword(it)).isValid())
    }

    println(passwordData.filter { result -> result }.size)

}