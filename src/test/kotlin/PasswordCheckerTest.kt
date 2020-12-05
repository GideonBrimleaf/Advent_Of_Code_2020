import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import utils.cleansePassword

class PasswordCheckerTest {

    @Test fun `Can Break Down Password`() {
        val password = PasswordChecker(4, 5, 'a', "abcdd")
        assertEquals(mutableMapOf('a' to 1, 'b' to 1, 'c' to 1, 'd' to 2), password.getBreakdown())
    }

    @Test fun `Can Check Against Policy`() {
        val password = PasswordChecker(4, 5, 'a', "abcdeaaa")
        assertTrue(password.isValid())
    }

    @Test fun `Can be made with a single string` () {
        val password = PasswordChecker(cleansePassword("1-3 a: abcde"))
        assertTrue(password.isValid())
    }

    @Test fun `Can validate with double digit qty`() {
        val password = PasswordChecker(cleansePassword("14-15 a: aaaaaaaaaaaaaab"))
        assertTrue(password.isValid())
    }

    @Test fun `Can count the number of valid passwords`() {
        val data = listOf("1-3 a: abcde", "1-3 b: cdefg", "2-9 c: ccccccccc")
        val passwordValidResults = data.map { passwordPolicy -> PasswordChecker(cleansePassword(passwordPolicy)).isValid() }
        assertEquals(2, passwordValidResults.filter { result -> result }.size)
    }

}