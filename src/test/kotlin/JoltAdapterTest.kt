import day10.getAdapterRating
import day10.getDistinctCombinations
import day10.getJoltDifferences
import day10.joltListToMap
import kotlin.test.Test
import kotlin.test.assertEquals

class JoltAdapterTest {

    private val testJolts1 = listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)

    private val joltMap = mapOf(
        0 to listOf(1, 2),
        1 to listOf(2, 4),
        2 to listOf(4, 5),
        4 to listOf(5),
        5 to listOf()
    )

    private val testJolts2 = listOf(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45,
        19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

    private val joltMap2 = mapOf(
        0 to listOf(1),
        1 to listOf(4),
        4 to listOf(5, 6, 7),
        5 to listOf(6, 7),
        6 to listOf(7),
        7 to listOf(10),
        10 to listOf(11, 12),
        11 to listOf(12),
        12 to listOf(15),
        15 to listOf(16),
        16 to listOf(19),
        19 to listOf(22),
        22 to listOf()
    )

    @Test fun `The in-built adapter is 3 higher then the max value` () {
        assertEquals(22, getAdapterRating(testJolts1))
    }

    @Test fun `Can get number of 1 and 3-jolt diffs` () {
        assertEquals(mapOf(1 to 7, 3 to 5), getJoltDifferences(testJolts1))
    }

    @Test fun `Can get number of 1 and 3-jolt diffs from larger set` () {
        assertEquals(mapOf(1 to 22, 3 to 10), getJoltDifferences(testJolts2))
    }

    @Test fun `Can get distinct combinations of jolt map` () {
        assertEquals(mapOf("count" to 5.toLong()), getDistinctCombinations(joltMap))
    }

    @Test fun `Can get distinct combinations of larger jolts` () {
        assertEquals(mapOf("count" to 8.toLong()), getDistinctCombinations(joltMap2))
    }

    @Test fun `Can convert list to map of children` () {
        assertEquals(joltMap2, joltListToMap(testJolts1))
    }

    @Test fun `Can get combinations on the biggest jolts` () {
        val mappedJoltList = joltListToMap(testJolts2)
        assertEquals(mapOf("count" to 19208.toLong()), getDistinctCombinations(mappedJoltList))
    }


}