import java.io.File

fun main(args: Array<String>) {
    val data = mutableListOf<Int>()

    File("./src/main/data/ExpenseCalc.txt").forEachLine {
        data.add(it.toInt())
    }

    val calculator = ExpenseCalc(data)

    println(calculator.calculate(2020, calculator.expenses))
    println(calculator.calculateThrees(2020))

}