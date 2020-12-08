import day4.*
import utils.cleansePassportsToList
import utils.readInPassportFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class PassportVerifierTest {
    private var mappedData = listOf<Map<String, String>>()
    private var mappedData2 = listOf<Map<String, String>>()
    private lateinit var singleValidPassport: Map<String, String>
    private lateinit var singleInvalidPassport: Map<String, String>

    @BeforeTest fun setup() {
        var cleansedData = mutableListOf<String>()
        readInPassportFile("./src/main/data/PassportsSample.txt", cleansedData)
        val splitData = cleansePassportsToList(cleansedData, " NEWLINE!! ").map { word -> word.split(" ")}
        mappedData = convertPassportListToMap(splitData)

        var cleansedData2 = mutableListOf<String>()
        readInPassportFile("./src/main/data/PassportsSample2.txt", cleansedData2)
        val splitData2 = cleansePassportsToList(cleansedData2, " NEWLINE!! ").map { word -> word.split(" ")}
        mappedData2 = convertPassportListToMap(splitData2)

        singleValidPassport = mapOf("pid" to "087499704", "hgt" to "74in", "ecl" to "grn", "iyr" to "2012", "eyr" to "2030", "byr" to "1980", "hcl" to "#b6652a")

        singleInvalidPassport = mapOf("pid" to "021572410", "hgt" to "182cm", "ecl" to "brn", "iyr" to "2012", "eyr" to "2020", "byr" to "1992", "hcl" to "dab227")
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

    @Test fun `Can determine a passport has all fields`() {
        assertEquals(true, containsValidKeys(singleValidPassport))
    }

    @Test fun `Can determine a passport has valid int data`() {
        assertEquals(true, containsValidIntData(singleValidPassport))
    }

    @Test fun `Can determine a passport has valid Height data`() {
        assertEquals(true, containsValidHeightData(singleValidPassport))
    }

    @Test fun `Can determine a passport has valid Passport data`() {
        assertEquals(true, containsValidPassportId(singleValidPassport))
    }

    @Test fun `Can determine a passport has valid Hair Colour`() {
        assertEquals(true, containsValidHairColour(singleValidPassport))
    }

    @Test fun `Can determine a passport has valid Eye Colour`() {
        assertEquals(true, containsValidEyeColour(singleValidPassport))
    }

    @Test fun `Can count valid passports with valid data`() {
        assertEquals(4, countValidPassportsAndValidData(mappedData2))
    }
}