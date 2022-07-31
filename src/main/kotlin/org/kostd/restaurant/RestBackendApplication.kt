package org.kostd.restaurant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RestBackendApplication

fun main(args: Array<String>) {
	runApplication<RestBackendApplication>(*args)
}
