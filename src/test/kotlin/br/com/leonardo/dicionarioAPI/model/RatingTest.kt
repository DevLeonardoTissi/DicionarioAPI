package br.com.leonardo.dicionarioAPI.model

import java.sql.Timestamp
import java.time.Instant

object RatingTest {
    fun build() = Rating(
        id = 1,
        rating = 5.00f,
        userEmail = "test@gmail.com",
        comment = "testComment",
        createdAt = Timestamp.from(Instant.now())
    )
}