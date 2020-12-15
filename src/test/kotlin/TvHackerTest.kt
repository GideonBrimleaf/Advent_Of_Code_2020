import day9.findOddOneOut
import day9.findOddOneOutComponents
import kotlin.test.Test
import kotlin.test.assertEquals

class TvHackerTest {

    private val numbers = listOf(35, 20, 15, 25, 47, 40, 62, 55, 65, 95,
        102, 117, 150, 182, 127, 219, 299, 277, 309, 576).map { it.toLong() }

    private val oddOneOutComponents = listOf(15,25,47,40).map {it.toLong()}

    @Test fun `Can detect odd one out` () {
        assertEquals(127, findOddOneOut(numbers, 5))
    }

    @Test fun `Can detect contiguous numbers which add to odd one out` () {
        assertEquals(oddOneOutComponents, findOddOneOutComponents(numbers, 127))
    }

    @Test fun `Can determine sum of min and max` () {
        val result = findOddOneOutComponents(numbers, 127)
        assertEquals(62, result.maxOrNull()?.plus(result.minOrNull()!!))
    }
}