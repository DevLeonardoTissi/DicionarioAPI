package br.com.leonardo.dicionarioAPI.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range

data class RatingForm(
    @field:NotEmpty val userEmail: String? = null,
    @field:NotNull @field:Range(min = 0, max = 5) val rating: Float? = null,
    val comment: String? = null
)