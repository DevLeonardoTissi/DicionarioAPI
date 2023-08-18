package br.com.leonardo.dicionarioAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class DicionarioApiApplication

fun main(args: Array<String>) {
    runApplication<DicionarioApiApplication>(*args)
}
