import day10.getAdapterRating
import day10.getJoltDifferences
import kotlin.test.Test
import kotlin.test.assertEquals

class JoltAdapterTest {

    private val testJolts1 = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)
    private val testJolts2 = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45,
        19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

    @Test fun `The in-built adapter is 3 higher then the max value` () {
        assertEquals(22, getAdapterRating(testJolts1))
    }

    @Test fun `Can get number of 1 and 3-jolt diffs` () {
        assertEquals(mapOf(1 to 7, 3 to 5), getJoltDifferences(testJolts1))
    }

    @Test fun `Can get number of 1 and 3-jolt diffs from larger set` () {
        assertEquals(mapOf(1 to 22, 3 to 10), getJoltDifferences(testJolts2))
    }
}