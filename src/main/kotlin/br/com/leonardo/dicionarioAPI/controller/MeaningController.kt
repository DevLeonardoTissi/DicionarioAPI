package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.service.MeaningService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/meanings")
class MeaningController(private val service: MeaningService) {
    @GetMapping("/{word}")
    fun searchMeaning(@PathVariable word: String): List<MeaningsResponse> {
        return service.searchMeaning(word)
    }
}


data class MeaningsResponse(
    var partOfSpeech: String? = null,
    val meanings: MutableList<String>? = null,
    var etymology: String? = null
)