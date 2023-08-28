package br.com.leonardo.dicionarioAPI.config

import br.com.leonardo.dicionarioAPI.security.JWTAuthenticationFilter
import br.com.leonardo.dicionarioAPI.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
@Suppress("unused")
class SecurityConfiguration(
    private val configuration: AuthenticationConfiguration,
    private val jwtUtil: JWTUtil
) {


    @Bean
    @Suppress("unused")
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.DELETE,"/rating/{id}").hasAuthority("READ_WRITE")
                it.requestMatchers(HttpMethod.POST, "/login")?.permitAll()
                it.anyRequest().permitAll()
            }
            .sessionManagement {
                SessionCreationPolicy.STATELESS
            }

            .headers { headersConfigure -> headersConfigure.frameOptions { it.disable() } }
            .addFilterBefore(
                JWTLoginFilter(configuration.authenticationManager, jwtUtil),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .addFilterBefore(
                JWTAuthenticationFilter(jwtUtil = jwtUtil),
                UsernamePasswordAuthenticationFilter().javaClass
            )
            .build()
    }


    @Bean
    @Suppress("unused")
    fun encoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }


}



