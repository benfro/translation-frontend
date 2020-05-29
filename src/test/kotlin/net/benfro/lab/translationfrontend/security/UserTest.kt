package net.benfro.lab.translationfrontend.security

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

internal class UserTest {

    val user = User("benfro", "B-E", "Fröberg", "frober@f.nu", "xoxo")

    @Test
    internal fun testIsCredNonExpired() {
        assertTrue(user.isCredentialsNonExpired())
    }

    @Test
    internal fun testIsCredExpired() {
        user.validTo = LocalDateTime.now().minusDays(3)
        assertFalse(user.isCredentialsNonExpired())
    }

    @Test
    internal fun testAddRole() {
        user.addRole(Role("USER"))
        assertEquals(1, user.roles.size)
    }
}
