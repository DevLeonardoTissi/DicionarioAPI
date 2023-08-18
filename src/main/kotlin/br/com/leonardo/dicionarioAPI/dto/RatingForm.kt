package br.com.leonardo.dicionarioAPI.dto

import br.com.leonardo.dicionarioAPI.utils.CLASSIFICATION_OUT_OF_RANGE_MESSAGE
import br.com.leonardo.dicionarioAPI.utils.NOT_EMPTY_FIELD_ERROR
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.Range

data class RatingForm(
    @field:NotEmpty(message = NOT_EMPTY_FIELD_ERROR)
    val userEmail: String,
    @field:NotNull @field:Range(
        min = 0,
        max = 5,
        message = CLASSIFICATION_OUT_OF_RANGE_MESSAGE
    )
    val rating: Float? = null,
    val comment: String? = null
)