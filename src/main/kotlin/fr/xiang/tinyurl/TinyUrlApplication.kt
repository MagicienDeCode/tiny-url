package fr.xiang.tinyurl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TinyUrlApplication

fun main(args: Array<String>) {
    runApplication<TinyUrlApplication>(*args)
}
