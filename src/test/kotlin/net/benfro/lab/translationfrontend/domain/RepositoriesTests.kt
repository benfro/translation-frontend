package net.benfro.lab.translationfrontend.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val translatorRepository: TranslatorRepository,
        val jobRepository: JobRepository) {

    @Test
    fun `When findByIdOrNull then return Job`() {
        val juergen = Translator("springjuergen", "Juergen", "Hoeller", "joergen@me.com")
        entityManager.persist(juergen)
        val article = Job("Spring Framework 5.0 goes GA")
        entityManager.persist(article)
        entityManager.flush()
        val found = jobRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return Translator`() {
        val juergen = Translator("springjuergen", "Juergen", "Hoeller", "joergen@me.com")
        entityManager.persist(juergen)
        entityManager.flush()
        val user = translatorRepository.findByLogin(juergen.login)
        assertThat(user).isEqualTo(juergen)
    }
}