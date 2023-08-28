package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.dto.RatingView
import br.com.leonardo.dicionarioAPI.exception.NotFoundException
import br.com.leonardo.dicionarioAPI.mapper.RatingFormMapper
import br.com.leonardo.dicionarioAPI.mapper.RatingViewMapper
import br.com.leonardo.dicionarioAPI.repository.RatingRepository
import br.com.leonardo.dicionarioAPI.utils.NOT_FOUND_RATING_EXCEPTION_MESSAGE
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RatingService(
    private val repository: RatingRepository,
    private val ratingFormMapper: RatingFormMapper,
    private val ratingViewMapper: RatingViewMapper
) {

    fun register(form: RatingForm): RatingView {
        val rating = ratingFormMapper.map(form)
        repository.save(rating)
        return ratingViewMapper.map(rating)
    }

    fun searchAll(userEmail: String?, pagination: Pageable): Page<RatingView> {
        val ratings = if (userEmail == null) {
            repository.findAll(pagination)
        } else {
            repository.searchByUserEmail(userEmail, pagination)
        }
        return ratings.map { rating ->
            ratingViewMapper.map(rating)
        }
    }

    fun searchById(id: Long): RatingView {
        val rating = repository.findById(id).orElseThrow { NotFoundException(NOT_FOUND_RATING_EXCEPTION_MESSAGE) }
        return ratingViewMapper.map(rating)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}