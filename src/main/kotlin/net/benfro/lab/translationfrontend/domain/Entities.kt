package net.benfro.lab.translationfrontend.domain

import net.benfro.lab.translationfrontend.util.toSlug
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "job")
class Job(
        var title: String,
        var addedAt: LocalDateTime = LocalDateTime.now(),
        var slug: String = title.toSlug(),
        var description: String? = null,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null)

@Entity
@Table(name = "translator")
class Translator(
        var login: String,
        var firstName: String,
        var lastName: String,
        var email: String,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null)
