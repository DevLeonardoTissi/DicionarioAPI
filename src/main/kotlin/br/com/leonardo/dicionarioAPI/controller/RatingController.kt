package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.dto.RatingView
import br.com.leonardo.dicionarioAPI.service.RatingService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/rating")
@Suppress("unused")
class RatingController(private val service: RatingService) {

    @PostMapping
    @Transactional
    fun sendRating(
        @RequestBody @Valid rating: RatingForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RatingView> {
        val ratingView = service.register(rating)
        val uri = uriBuilder.path("/rating/${ratingView.id}").build().toUri()
        return ResponseEntity.created(uri).body(ratingView)
    }

    @GetMapping("/all")
    fun searchAll(): List<RatingView> = service.searchAll()

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): RatingView {
        return service.searchById(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}