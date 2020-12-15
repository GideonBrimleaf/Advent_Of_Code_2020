import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import day3.tobogganTraversal
import utils.fileToMutableList

class TobogganTraversalTest() {

    private val testData = fileToMutableList("./src/main/data/TreeMapSample2.txt")

    @Test fun `Can test sample data`() {
        assertEquals(11, testData.size)
    }

    @Test fun `Test data has 7 trees`() {
        assertEquals(7, tobogganTraversal(testData, 3, 1))
    }

    @Test fun `Can Traverse Multiple Times`() {
        assertEquals(336,
            tobogganTraversal(testData, 1,1) *
                    tobogganTraversal(testData, 3,1) *
                    tobogganTraversal(testData, 5, 1) *
                    tobogganTraversal(testData, 7, 1) *
                    tobogganTraversal(testData, 1, 2)
        )
    }
}