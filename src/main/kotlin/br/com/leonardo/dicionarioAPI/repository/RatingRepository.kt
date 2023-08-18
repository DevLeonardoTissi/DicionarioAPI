package br.com.leonardo.dicionarioAPI.repository

import br.com.leonardo.dicionarioAPI.model.Rating
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RatingRepository : JpaRepository<Rating, Long>{

    fun searchByUserEmail(userEmail:String, pagination:Pageable): Page<Rating>

}