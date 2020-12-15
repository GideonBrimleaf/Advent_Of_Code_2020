import day8.infiniteLoopCounter
import day8.pairData
import kotlin.test.Test
import kotlin.test.assertEquals

class InfiniteLooperTest {

    private val testData = listOf( "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4", "acc +6")

    private val transformedData = listOf("nop" to 0, "acc" to 1, "jmp" to 4, "acc" to 3,
        "jmp" to -3, "acc" to -99, "acc" to 1, "jmp" to -4, "acc" to 6)

    @Test fun `Can count number at point of loop` () {
        assertEquals(5, infiniteLoopCounter(transformedData))
    }

    @Test fun `Can convert data to paired list` () {
        assertEquals(transformedData, pairData(testData))
    }

}