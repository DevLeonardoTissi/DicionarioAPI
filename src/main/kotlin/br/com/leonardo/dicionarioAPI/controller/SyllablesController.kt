package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.service.SyllablesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/syllables")
class SyllablesController(private val service:SyllablesService) {
    @GetMapping("/{word}")
    fun searchSyllables(@PathVariable word:String):List<String>{
        return service.searchSyllables(word)
    }
}