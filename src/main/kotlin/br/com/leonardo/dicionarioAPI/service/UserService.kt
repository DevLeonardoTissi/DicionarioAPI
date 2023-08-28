package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.exception.NotFoundException
import br.com.leonardo.dicionarioAPI.repository.UserRepository
import br.com.leonardo.dicionarioAPI.utils.NOT_FOUND_USER_EXCEPTION_MESSAGE
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Suppress("unused")
class UserService(private val repository: UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val user = repository.findByEmail(username) ?: throw NotFoundException(NOT_FOUND_USER_EXCEPTION_MESSAGE)
        return UserDetail(user)
    }
}