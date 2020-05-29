package net.benfro.lab.translationfrontend.util

import net.benfro.lab.translationfrontend.domain.Job
import net.benfro.lab.translationfrontend.domain.JobRepository
import net.benfro.lab.translationfrontend.domain.Translator
import net.benfro.lab.translationfrontend.domain.TranslatorRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class AppConfiguration {

    @Bean
    fun databaseInitializer(translatorRepository: TranslatorRepository,
                            jobRepository: JobRepository) = ApplicationRunner {

        val smaldini = translatorRepository.save(Translator("smaldini", "Stéphane", "Maldini", "smal@ball.com"))
        jobRepository.save(Job(
                title = "Translations 2020a",
                addedAt = LocalDateTime.now(),
                description = "This is a short description"
        ))
        jobRepository.save(Job(
                title = "Translations 2017b",
                addedAt = LocalDateTime.now().minusDays(463),
                description = "Another cool description!"
        ))
    }
}