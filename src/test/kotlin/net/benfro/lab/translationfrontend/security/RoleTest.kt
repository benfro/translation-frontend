package net.benfro.lab.translationfrontend.security

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.security.core.GrantedAuthority


internal class RoleTest {

    @Test
    fun addAuthority() {
        val of = Role.of("ADMIN")
        of.addAuthority(Authority("ADD_TEST"))
        assertEquals("ADD_TEST", (of.authorities.first() as GrantedAuthority).authority)
        assertEquals(1, of.authorities.size)
    }

    @Test
    fun removeAuthority() {
        val of = Role.of("ADMIN")
        val auth = Authority("ADD_TEST")
        of.addAuthority(auth)
        of.removeAuthority(auth)
        assertEquals(0, of.authorities.size)
    }

}

