package nl.hva.vocability

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VocabilityApplication

fun main(args: Array<String>) {
    runApplication<VocabilityApplication>(*args)
}
