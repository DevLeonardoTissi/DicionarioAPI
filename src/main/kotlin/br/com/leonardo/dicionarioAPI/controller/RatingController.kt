package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.service.RatingService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rating")
@Suppress("unused")
class RatingController(private val service:RatingService) {

    @PostMapping
    fun sendRating(@RequestBody @Valid rating:RatingForm){
        service.sendRating(rating)
    }
}