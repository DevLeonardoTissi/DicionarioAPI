package br.com.leonardo.dicionarioAPI.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/synonyms")
class SynonymsController {

    @GetMapping("/{word}")
    fun searchSynonyms(@PathVariable word: String): String {
        return word

    }

}