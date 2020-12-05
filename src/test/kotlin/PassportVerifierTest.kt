import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PassportVerifierTest {
    private val data = mutableListOf<String>()
    private var processedData = listOf<String>()


    @BeforeTest fun setup() {
        File("./src/main/data/PassportsSample.txt").forEachLine {
            if(it == "") {
                data.add("NEWLINE!!")
            } else {
                data.add(it)
            }
            processedData = data.joinToString().split("NEWLINE!!")
        }
    }

    @Test fun `There are four lights`() {
        assertEquals(4, processedData.size)
    }
}