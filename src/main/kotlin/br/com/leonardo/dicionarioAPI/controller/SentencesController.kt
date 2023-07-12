package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.dto.SentenceView
import br.com.leonardo.dicionarioAPI.service.SentencesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sentences")
@Suppress("unused")
class SentencesController(private val service:SentencesService) {
    @GetMapping("/{word}")
    fun searchSentences(@PathVariable word: String): List<SentenceView> {
       return service.searchSentences(word)
    }
}