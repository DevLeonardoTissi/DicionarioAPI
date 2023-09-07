package br.com.leonardo.dicionarioAPI.repository

import br.com.leonardo.dicionarioAPI.dto.RatingByUserEmailDTO
import br.com.leonardo.dicionarioAPI.model.Rating
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long>{

    fun searchByUserEmail(userEmail:String, pagination:Pageable): Page<Rating>

    @Query("SELECT new br.com.leonardo.dicionarioAPI.dto.RatingByUserEmailDTO(userEmail, COUNT(t)) FROM Rating t GROUP BY t.userEmail")
    fun fetchRatingCountsByUserEmail() : List<RatingByUserEmailDTO>
}