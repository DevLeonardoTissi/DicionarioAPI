package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
@Suppress("unused")
class UserService(private val repository: UserRepository): UserDetailsService {


    //CRIAR UM NOVO TIPO DE EXCEPTION PARA QUANDO NÃO ACHAR O USUÁRIO

    override fun loadUserByUsername(username: String?): UserDetails {
       val user = repository.findByEmail(username) ?: throw RuntimeException()
        println(user)
       return UserDetail(user)
    }
}