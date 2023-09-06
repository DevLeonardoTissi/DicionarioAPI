package br.com.leonardo.dicionarioAPI.model

import br.com.leonardo.dicionarioAPI.dto.RatingView
import java.sql.Timestamp
import java.time.Instant

object RatingViewTest {
    fun build() = RatingView(
        userEmail = "test@gmail.com",
        rating = 5.00f,
        comment = "testComment",
        createdAt = Timestamp.from(Instant.now())
    )
}