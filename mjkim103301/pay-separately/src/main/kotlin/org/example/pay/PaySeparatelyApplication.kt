package org.example.pay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PaySeparatelyApplication

fun main(args: Array<String>) {
	runApplication<PaySeparatelyApplication>(*args)
}
