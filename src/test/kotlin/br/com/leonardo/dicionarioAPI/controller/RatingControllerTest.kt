package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.config.JWTUtil
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RatingControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var token:String? = null

    @Autowired
    private lateinit var jwtUtil:JWTUtil

    companion object {
        private const val END_POINT_RATING = "/rating/"
        private const val END_POINT_RATING_ID = END_POINT_RATING.plus("%s")
    }

    @BeforeEach
    fun setup() {

        token = generateTokenForTest()

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }


    @Test
    fun `should return code 400 when request without token`() {
        mockMvc.delete(END_POINT_RATING_ID.format(5)).andExpect { status { is4xxClientError() } }
    }


    @Test
    fun `should return code 200 when rating is found`() {
        mockMvc.get(END_POINT_RATING_ID.format(3)).andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 400 when rating not found`() {
        mockMvc.get(END_POINT_RATING_ID.format(99)).andExpect { status { is4xxClientError() } }
    }


    @Test
    fun `should return code 200 when correct token`(){
        mockMvc.delete(END_POINT_RATING_ID.format(1)){
           headers { token?.let { this.setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }


    private fun generateTokenForTest(): String? {
        val authorities = mutableListOf(br.com.leonardo.dicionarioAPI.model.Role(id = 1,"READ_WRITE" ))
       return jwtUtil.generateToken("leonardo_spring_boot@gmail.com", authorities = authorities )
    }

}