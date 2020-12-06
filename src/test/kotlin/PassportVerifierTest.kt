import utils.cleansePassportsToList
import day4.convertPassportListToMap
import day4.countValidPassports
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PassportVerifierTest {
    private var mappedData = listOf<Map<String, String>>()

    @BeforeTest fun setup() {
        var cleansedData = mutableListOf<String>()
        File("./src/main/data/PassportsSample.txt").forEachLine {
            if (it == "") {
                cleansedData.add("NEWLINE!!")
            } else {
                cleansedData.add(it)
            }
        }
        val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
        mappedData = convertPassportListToMap(splitData)
    }

    @Test fun `There are four lights`() {
        assertEquals(4, mappedData.size)
    }

    @Test fun `Can convert to map`() {
        assertEquals(mapOf("eyr" to "2020"), mappedData.first().filterKeys { it == "eyr" })
    }

    @Test fun `Can count valid passport entries`() {
        assertEquals(2, countValidPassports(mappedData))
    }
}