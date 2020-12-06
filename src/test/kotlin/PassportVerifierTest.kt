import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PassportVerifierTest {
    private var data = mutableListOf<String>()
    private var mappedData = mutableListOf<Map<String, String>>()

    @BeforeTest fun setup() {
        File("./src/main/data/PassportsSample.txt").forEachLine {
            if (it == "") {
                data.add("NEWLINE!!")
            } else {
                data.add(it)
            }
        }

        data = data.joinToString().replace(",", "").split(" NEWLINE!! ").toMutableList()
    }

    @Test fun `There are four lights`() {
        assertEquals(4, data.size)
    }

    @Test fun `Can convert to map`() {
        val splitData = data.map { word -> word.split(" ")}

        splitData.forEach {
            val mapValue = it.associate { it.substringBefore(":") to it.substringAfter(":") }
            mappedData.add(mapValue)
        }

        assertEquals(mapOf("eyr" to "2020"), mappedData.first().filterKeys { it == "eyr" })
    }
}