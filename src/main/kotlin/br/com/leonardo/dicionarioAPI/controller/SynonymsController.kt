package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.service.SynonymsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/synonyms")
@Suppress("unused")
class SynonymsController(private val service: SynonymsService) {
    @GetMapping("/{word}")
    fun searchSynonyms(@PathVariable word: String): List<String> {
        return service.searchSynonyms(word)
    }
}