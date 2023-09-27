package br.com.leonardo.dicionarioAPI.integration

import br.com.leonardo.dicionarioAPI.configuration.DatabaseContainerConfiguration
import br.com.leonardo.dicionarioAPI.model.Rating
import br.com.leonardo.dicionarioAPI.model.RatingTest
import br.com.leonardo.dicionarioAPI.repository.RatingRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RatingRepositoryTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var ratingRepository: RatingRepository
    private val ratingTest = RatingTest.build()

    @Test
    fun `must add a rating in the database created in the temporary container and check its respective type`() {
        ratingRepository.save(ratingTest)
        val ratings = ratingRepository.findAll()

        assertThat(ratings).isNotNull
        assertThat(ratings.first()).isExactlyInstanceOf(Rating::class.java)
    }

    @Test
    fun `should return a rating with email equivalent`() {
        ratingRepository.save(ratingTest)
        val rating = ratingRepository.searchByUserEmail(ratingTest.userEmail, PageRequest.of(0, 5))

        assertThat(rating).isNotNull

    }

    @Test
    fun `should create and delete database rating`() {
        ratingRepository.save(ratingTest)
        ratingRepository.deleteAll()
        val ratingsList = ratingRepository.findAll()

        assertThat(ratingsList).isEmpty()
    }

}