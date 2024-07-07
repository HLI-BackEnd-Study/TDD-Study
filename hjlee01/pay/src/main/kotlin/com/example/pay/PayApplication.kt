package com.example.pay

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PayApplication

fun main(args: Array<String>) {
	runApplication<PayApplication>(*args)
}
