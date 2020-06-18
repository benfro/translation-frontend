package net.benfro.lab.translationfrontend.security

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class SecurityRepositoriesTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val roleRepository: RoleRepository,
        val authRepository: AuthorityRepository) {

    @Test
    fun `find by username works`() {
        val user = User("benfro", "apa", "Bengt-Erik", "Fröberg", "bengan.froberg@gmail.com")
        entityManager.persist(user)
        entityManager.flush()
        val found = userRepository.findByUsername("benfro")
        Assertions.assertThat(found).isEqualTo(user)
    }

    @Test
    fun `users gets roles and they are retrieved`() {
        val user = User("benfro", "apa", "Bengt-Erik", "Fröberg", "bengan.froberg@gmail.com")
        val roleUser = Role("USER")
        val roleAdmin = Role("ADMIN")
        val authCreate = Authority("create stuff")
        val authDelete = Authority("delete stuff")
        val authEdit = Authority("edit stuff")

        entityManager.persist(authCreate)
        entityManager.persist(authDelete)
        entityManager.persist(authEdit)

        roleUser.addAuthority(authCreate)
        roleUser.addAuthority(authEdit)
        entityManager.persist(roleUser)

        roleAdmin.addAuthority(authDelete)
        entityManager.persist(roleAdmin)
        user.addRole(roleUser)
        user.addRole(roleAdmin)
        entityManager.persist(user)

        entityManager.flush()

        val found = userRepository.findByUsername("benfro")
        assertThat(found.roles.size).isEqualTo(2)
    }
}