package br.com.leonardo.dicionarioAPI.mapper

import br.com.leonardo.dicionarioAPI.dto.RatingForm
import br.com.leonardo.dicionarioAPI.model.Rating
import org.springframework.stereotype.Component

@Component
class RatingFormMapper :Mapper<RatingForm, Rating> {
    override fun map(t: RatingForm): Rating {
        return Rating(
            userEmail = t.userEmail,
            rating = t.rating,
            comment = t.comment
        )
    }
}