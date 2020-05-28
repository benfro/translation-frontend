package net.benfro.lab.translationfrontend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TranslationFrontendApplication

fun main(args: Array<String>) {
	runApplication<TranslationFrontendApplication>(*args)
}
