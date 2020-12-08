import day6.getDistinctAnswers
import day6.getSumOfDistinctAnswers
import utils.cleanseQuestionnaireToList
import utils.readInMultiLineFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class QuestionnaireCalculatorTest {
    private var splitQuestionnaireData = mutableListOf<String>()

    @BeforeTest fun setup() {
        var cleansedQuestionnaireData = mutableListOf<String>()
        readInMultiLineFile("./src/main/data/QuestionnaireSample.txt", cleansedQuestionnaireData)
        splitQuestionnaireData = cleanseQuestionnaireToList(cleansedQuestionnaireData, "NEWLINE!!")
    }

    @Test fun `Can get distinct list of letters`() {
        assertEquals(listOf("abc", "abc", "abc", "a", "b"), getDistinctAnswers(splitQuestionnaireData))
    }

    @Test fun `Can sum up unique answers`() {
        val distinctAnswers = getDistinctAnswers(splitQuestionnaireData)
        assertEquals(11, getSumOfDistinctAnswers(distinctAnswers))
    }
}