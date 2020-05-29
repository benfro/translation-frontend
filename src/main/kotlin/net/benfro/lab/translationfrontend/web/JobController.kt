package net.benfro.lab.translationfrontend.web

import net.benfro.lab.translationfrontend.domain.Job
import net.benfro.lab.translationfrontend.domain.JobRepository
import net.benfro.lab.translationfrontend.util.format
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime

@Controller
class JobController(private val jobRepository: JobRepository) {

    @GetMapping("/")
    fun home (model: Model): String {
        model["title"] = "Welcome to Translation Front End Application"
        model["jobs"] = jobRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "index"
    }

    @GetMapping("/job/list")
    fun displayJobList (model: Model): String {
        model["title"] = "Welcome to Translation Front End Application"
        model["jobs"] = jobRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "jobList"
    }

    @GetMapping("/job/{slug}")
    fun displayJob(@PathVariable slug: String, model: Model): String {
        val job = jobRepository
                .findBySlug(slug)
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This job does not exist")
        model["title"] = job.title
        model["job"] = job
        return "job"
    }

    @GetMapping("/job/new")
    fun jobForm(model: Model): String {
        model["job"] = Job("", LocalDateTime.now())
        return "jobForm"
    }

    @PostMapping("/job/new")
    fun jobSubmit(@ModelAttribute("job") job: Job, model: Model):String {
        jobRepository.save(job)
        return "redirect:/"
    }

    fun Job.render() = RenderedJob(
            slug,
            title,
            addedAt.format(),
            description
    )

    data class RenderedJob(
            val slug: String,
            val title: String,
            val addedAt: String,
            val description: String? = "<No description available>"
            //val author: Translator
    )
}
