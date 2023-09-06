package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.exception.NotFoundException
import br.com.leonardo.dicionarioAPI.mapper.RatingFormMapper
import br.com.leonardo.dicionarioAPI.mapper.RatingViewMapper
import br.com.leonardo.dicionarioAPI.model.Rating
import br.com.leonardo.dicionarioAPI.model.RatingTest
import br.com.leonardo.dicionarioAPI.model.RatingViewTest
import br.com.leonardo.dicionarioAPI.repository.RatingRepository
import br.com.leonardo.dicionarioAPI.utils.NOT_FOUND_RATING_EXCEPTION_MESSAGE
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class RatingServiceTest {

    val rating = PageImpl(listOf(RatingTest.build()))

    val pageable: Pageable = mockk()


    val repository: RatingRepository = mockk {

        every { searchByUserEmail(any(), any()) } returns rating
        every { findAll(pageable) } returns rating

    }

    val ratingFormMapper: RatingFormMapper = mockk()
    val ratingViewMapper: RatingViewMapper = mockk {

        every { map(any()) } returns RatingViewTest.build()

    }

    val ratingService = RatingService(repository, ratingFormMapper, ratingViewMapper)

    val ratingSlot = slot<Rating>()


    @Test
    fun `should list ratings from email`() {

        ratingService.searchAll("teste@gmail.com", pageable)
        verify(exactly = 1) { repository.searchByUserEmail(any(), any()) }
        verify(exactly = 0) { repository.findAll(pageable) }
        verify(exactly = 1) { ratingViewMapper.map(any()) }

    }

    @Test
    fun `should list ratings from email with slot`() {
        every { ratingViewMapper.map(capture(ratingSlot)) } returns RatingViewTest.build()

        ratingService.searchAll("teste@gmail.com", pageable)
        verify(exactly = 1) { repository.searchByUserEmail(any(), any()) }
        verify(exactly = 0) { repository.findAll(pageable) }
        verify(exactly = 1) { ratingViewMapper.map(any()) }

        val rating = RatingTest.build()
        assertThat(ratingSlot.captured.userEmail).isEqualTo(rating.userEmail)
    }

    @Test
    fun `should list all ratings`() {
        ratingService.searchAll(null, pageable)
        verify(exactly = 0) { repository.searchByUserEmail(any(), any()) }
        verify(exactly = 1) { repository.findAll(pageable) }
        verify(exactly = 1) { ratingViewMapper.map(any()) }
    }

    @Test
    fun `must throw not found exception when rating is not found`() {
        every { repository.findById(any()) } returns Optional.empty()

        val notFoundException = assertThrows<NotFoundException> {
            ratingService.searchById(99)
        }
        assertThat(notFoundException.message).isEqualTo(NOT_FOUND_RATING_EXCEPTION_MESSAGE)
    }


}