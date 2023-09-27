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
class SynonymsControllerTest : DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object {
        private const val END_POINT_SYNONYMS = "/syllables/"
        private const val END_POINT_SYNONYMS_EXISTENT = END_POINT_SYNONYMS.plus("cavalo")
        private const val END_POINT_SYNONYMS_NOT_EXISTENT = END_POINT_SYNONYMS.plus("asdasdasd")
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `should return code 400 when no synonyms found`() {
        mockMvc.get(END_POINT_SYNONYMS_NOT_EXISTENT).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when synonyms are found`() {
        mockMvc.get(END_POINT_SYNONYMS_EXISTENT).andExpect { status { is2xxSuccessful() } }
    }

}