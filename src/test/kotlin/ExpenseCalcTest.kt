import day1.ExpenseCalc
import kotlin.test.Test
import kotlin.test.assertEquals

class ExpenseCalcTest {

    @Test fun twosome() {
        val data = mutableListOf(1721,
            979,
            366,
            299,
            675,
            1456)

        val calculator = ExpenseCalc(data)

        assertEquals(514579, calculator.calculate(2020, calculator.expenses))
    }

    @Test fun threesome() {
        val data = mutableListOf(1721,
            979,
            366,
            299,
            675,
            1456)

        val calculator = ExpenseCalc(data)

        assertEquals(241861950, calculator.calculateThrees(2020))
    }
}