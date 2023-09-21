package br.com.leonardo.dicionarioAPI

import br.com.leonardo.dicionarioAPI.configuration.DatabaseContainerConfiguration
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
class DicionarioApiApplicationTests: DatabaseContainerConfiguration() {

	@Test
	fun contextLoads() {
	}

}
