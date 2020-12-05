import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PasswordCheckerTest {

    @Test fun canBreakDownPassword() {
        val password = PasswordChecker(4, 5, 'a', "abcdd")
        assertEquals(mutableMapOf('a' to 1, 'b' to 1, 'c' to 1, 'd' to 2), password.getBreakdown())
    }

    @Test fun canCheckBasicPolicy() {
        val password = PasswordChecker(4, 5, 'a', "abcdeaaa")
        assertTrue(password.isValid())
    }

}