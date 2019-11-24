package com.microservices.swarmed

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SwarmedApplication

fun main(args: Array<String>) {
	runApplication<SwarmedApplication>(*args)
}
