package net.benfro.lab.translationfrontend.security

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class UserTest {

    val user = User("benfro", "B-E", "Fröberg", "frober@f.nu", "xoxo")

    @BeforeEach
    fun setUp() {
    }

    @Test
    internal fun testIsCredNonExpired() {
        assertTrue(user.isCredentialsNonExpired())
    }

    @Test
    internal fun testIsCredExpired() {
        user.validTo = LocalDateTime.now().minusDays(3)
        assertFalse(user.isCredentialsNonExpired())
    }
}
