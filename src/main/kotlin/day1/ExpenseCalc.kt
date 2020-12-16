package day1

class ExpenseCalc(val expenses : List<Int>) {
    fun calculate(target: Int, listToIterate : List<Int>): Int {
        return listToIterate.fold(0, {acc, num ->
            val remainder = target - num
            if (listToIterate.contains(remainder)) {
                return acc + num * remainder
            }
            acc
        })
    }

    fun calculateThrees(target : Int): Int {
        return expenses.fold(0, {acc, num ->
            val remainder = target - num
            val remainingList = expenses.toMutableList()
            remainingList.remove(num)
            val calculateResidual = calculate(remainder, remainingList)
            if (calculateResidual != 0) {
                return acc + calculateResidual * num
            }
            acc
        })
    }
}