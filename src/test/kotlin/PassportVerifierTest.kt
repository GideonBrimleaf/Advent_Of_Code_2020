import utils.cleansePassportsToList
import day4.convertPassportListToMap
import day4.countValidPassports
import utils.readInPassportFile
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PassportVerifierTest {
    private var mappedData = listOf<Map<String, String>>()
    private var mappedData2 = listOf<Map<String, String>>()

    @BeforeTest fun setup() {
        var cleansedData = mutableListOf<String>()
        readInPassportFile("./src/main/data/PassportsSample.txt", cleansedData)
        val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
        mappedData = convertPassportListToMap(splitData)

        var cleansedData2 = mutableListOf<String>()
        readInPassportFile("./src/main/data/PassportsSample2.txt", cleansedData2)
        val splitData2 = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
        mappedData2 = convertPassportListToMap(splitData2)
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

    @Test fun `Can count valid passports with valid data`() {

    }
}