import day6.*
import utils.Breakdownable
import utils.cleanseQuestionnaireToList
import utils.readInMultiLineFile
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class QuestionnaireCalculatorTest {
    private var splitQuestionnaireData = mutableListOf<String>()
    private var cleansedQuestionnaireData = mutableListOf<String>()
    private var groupedQuestionnaires = listOf<List<String>>()

    @BeforeTest fun setup() {
        readInMultiLineFile("./src/main/data/QuestionnaireSample.txt", cleansedQuestionnaireData)
        splitQuestionnaireData = cleanseQuestionnaireToList(cleansedQuestionnaireData, "NEWLINE!!")
        groupedQuestionnaires = getGroupedAnswers(cleansedQuestionnaireData)
    }

    @Test fun `Can get distinct list of letters`() {
        assertEquals(listOf("abc", "abc", "abc", "a", "b"), getDistinctAnswers(splitQuestionnaireData))
    }

    @Test fun `Can sum up unique answers`() {
        val distinctAnswers = getDistinctAnswers(splitQuestionnaireData)
        assertEquals(11, getSumOfDistinctAnswers(distinctAnswers))
    }

    @Test fun `Can see number of questions answered yes in group with one person` () {
        assertEquals(3, getAllYesAnswers(groupedQuestionnaires[0], splitQuestionnaireData[0]))
    }

    @Test fun `Can see number of questions answered yes in group with multiple people` () {
        assertEquals(1, getAllYesAnswers(groupedQuestionnaires[2], splitQuestionnaireData[2]))
    }

    @Test fun `Can sum up all questions that everyone in a group answered yes to` () {
        assertEquals(6, getSumOfAllYesAnswers(groupedQuestionnaires, splitQuestionnaireData))
    }

}