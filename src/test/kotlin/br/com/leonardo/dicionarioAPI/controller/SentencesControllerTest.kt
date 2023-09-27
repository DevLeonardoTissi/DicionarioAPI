package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.configuration.DatabaseContainerConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SentencesControllerTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object {
        private const val END_POINT_SENTENCES = "/sentences/"
        private const val END_POINT_SENTENCES_EXISTENT = END_POINT_SENTENCES.plus("papagaio")
        private const val END_POINT_SENTENCES_NOT_EXISTENT = END_POINT_SENTENCES.plus("asdasdasd")
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `should return code 400 when no sentences found`() {
        mockMvc.get(END_POINT_SENTENCES_NOT_EXISTENT).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when there are sentences found`() {
        mockMvc.get(END_POINT_SENTENCES_EXISTENT).andExpect { status { is2xxSuccessful() } }
    }


}