package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import org.springframework.stereotype.Service

@Service
class RatingService{

    fun sendRating(
         rating:RatingForm) {
        println(rating.toString())
    }
}