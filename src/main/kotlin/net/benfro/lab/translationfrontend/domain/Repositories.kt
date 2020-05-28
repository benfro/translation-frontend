package net.benfro.lab.translationfrontend.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface JobRepository : CrudRepository<Job, Long> {
    fun findBySlug(slug: String): Job?
    fun findAllByOrderByAddedAtDesc(): Iterable<Job>
}

@Repository
interface TranslatorRepository : CrudRepository<Translator, Long> {
    fun findByLogin(login: String): Translator?
}