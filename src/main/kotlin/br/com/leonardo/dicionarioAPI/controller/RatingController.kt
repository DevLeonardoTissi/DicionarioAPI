package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.dto.RatingView
import br.com.leonardo.dicionarioAPI.service.RatingService
import br.com.leonardo.dicionarioAPI.utils.RATINGS_CACHE_KEY
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/rating")
@Suppress("unused")
class RatingController(private val service: RatingService) {

    @PostMapping
    @Transactional
    @CacheEvict(value = [RATINGS_CACHE_KEY], allEntries = true)
    fun register(
        @RequestBody @Valid rating: RatingForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<RatingView> {
        val ratingView = service.register(rating)
        val uri = uriBuilder.path("/rating/${ratingView.id}").build().toUri()
        return ResponseEntity.created(uri).body(ratingView)
    }

    @GetMapping()
    @Cacheable(RATINGS_CACHE_KEY)
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
    @CacheEvict(value = [RATINGS_CACHE_KEY], allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

}