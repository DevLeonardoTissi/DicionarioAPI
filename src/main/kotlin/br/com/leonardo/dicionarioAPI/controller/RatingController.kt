package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.dto.RatingView
import br.com.leonardo.dicionarioAPI.service.RatingService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/rating")
@Suppress("unused")
class RatingController(private val service: RatingService) {

    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid rating: RatingForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RatingView> {
        val ratingView = service.register(rating)
        val uri = uriBuilder.path("/rating/${ratingView.id}").build().toUri()
        return ResponseEntity.created(uri).body(ratingView)
    }

    @GetMapping
    fun searchAll(
        @RequestParam(required = false) userEmail: String?,
        @PageableDefault(size = 5, sort = ["createdAt"], direction = Sort.Direction.DESC) pagination: Pageable
    ): Page<RatingView> = service.searchAll(userEmail, pagination)

    @GetMapping("/{id}")
    fun searchById(@PathVariable id: Long): RatingView {
        return service.searchById(id)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}