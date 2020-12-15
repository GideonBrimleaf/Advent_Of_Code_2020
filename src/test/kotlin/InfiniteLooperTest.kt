import day8.deLoop
import day8.infiniteLoopCounter
import day8.pairData
import kotlin.test.Test
import kotlin.test.assertEquals

class InfiniteLooperTest {

    private val testData = listOf( "nop +0", "acc +1", "jmp +4", "acc +3", "jmp -3", "acc -99", "acc +1", "jmp -4", "acc +6")

    private val transformedData = listOf(0 to "nop", 1 to "acc", 4 to "jmp", 3 to "acc",
        -3 to "jmp", -99 to "acc", 1 to "acc", -4 to "jmp", 6 to "acc")

    private val nonLoopingData = listOf(0 to "nop", 1 to "acc", 4 to "jmp", 3 to "acc",
        -3 to "jmp", -99 to "acc", 1 to "acc", -4 to "nop", 6 to "acc")

    @Test fun `Can count number at point of loop` () {
        assertEquals(5 to true, infiniteLoopCounter(transformedData))
    }

    @Test fun `Can convert data to paired list` () {
        assertEquals(transformedData, pairData(testData))
    }

    @Test fun `Can detect no infinite loop` () {
        assertEquals(8 to false, infiniteLoopCounter(nonLoopingData))
    }

    @Test fun `Can transform an infinite loop to non looping` () {
        assertEquals(nonLoopingData, deLoop(transformedData))
    }

}