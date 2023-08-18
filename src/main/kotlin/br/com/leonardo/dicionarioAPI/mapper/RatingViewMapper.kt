package br.com.leonardo.dicionarioAPI.mapper

import br.com.leonardo.dicionarioAPI.dto.RatingView
import br.com.leonardo.dicionarioAPI.model.Rating
import org.springframework.stereotype.Component

@Component
class RatingViewMapper : Mapper<Rating, RatingView> {
    override fun map(t: Rating): RatingView {
        return RatingView(
            id = t.id,
            userEmail = alterCharacters(t.userEmail),
            rating = t.rating,
            comment = t.comment,
            createdAt = t.createdAt

        )
    }

    private fun alterCharacters(userEmail: String): String {
        val maskedPortion = "**********"
        val visiblePortion = userEmail.substring(0, userEmail.length - 10)
        return "$visiblePortion$maskedPortion"
    }
}