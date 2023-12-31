package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.config.JWTUtil
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class RatingControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    companion object {
        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
        }

        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testedb")
            withUsername("tester")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)

            registry.add("spring.data.redis.host", redisContainer::getHost)
            registry.add("spring.data.redis.port", redisContainer::getFirstMappedPort)
        }

        private const val END_POINT_RATING = "/rating"
        private const val END_POINT_RATING_ID = END_POINT_RATING.plus("/%s")
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
    @Order(1)
    fun `should return code 200 when rating is found`() {
        mockMvc.get(END_POINT_RATING_ID.format(1)).andExpect { status { is2xxSuccessful() } }
    }

    @Test
    @Order(2)
    fun `should return all ratings `() {
        mockMvc.get(END_POINT_RATING).andExpect { status { is2xxSuccessful() } }
    }

    @Test
    @Order(3)
    fun `should return code 400 when request without token`() {
        mockMvc.delete(END_POINT_RATING_ID.format(1)).andExpect { status { is4xxClientError() } }
    }

    @Test
    @Order(4)
    fun `should return code 400 when rating not found`() {
        mockMvc.get(END_POINT_RATING_ID.format(99)).andExpect { status { is4xxClientError() } }
    }

    @Test
    @Order(5)
    fun `should return code 200 when correct token`() {
        mockMvc.delete("/rating/1") {
            headers { token?.let { this.setBearerAuth(it) } }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateTokenForTest(): String? {
        val authorities = mutableListOf(br.com.leonardo.dicionarioAPI.model.Role(id = 1, "READ_WRITE"))
        return jwtUtil.generateToken("leonardo_spring_boot_api_test@email.com", authorities = authorities)
    }

}