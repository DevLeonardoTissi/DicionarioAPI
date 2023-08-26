package br.com.leonardo.dicionarioAPI.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@Suppress("unused")
class SecurityConfiguration {


    @Bean
    @Suppress("unused")
     fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers(HttpMethod.POST, "/rating").hasAuthority("READ_WRITE")
                it.requestMatchers(HttpMethod.DELETE, "/rating").hasAuthority("READ_WRITE")
                it.requestMatchers(HttpMethod.DELETE,"/rating/{id}").hasAuthority("READ_WRITE")
                it.anyRequest().permitAll()
            }
            .sessionManagement{
                 SessionCreationPolicy.STATELESS
            }

            .headers { headersConfigure -> headersConfigure.frameOptions { it.disable() } }
            .formLogin { it.disable() }
            .httpBasic {  }
            .build()
    }



    @Bean
    @Suppress("unused")
    fun encoder() : PasswordEncoder {
        return BCryptPasswordEncoder()
    }




}



