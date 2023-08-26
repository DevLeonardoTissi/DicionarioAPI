package br.com.leonardo.dicionarioAPI.repository

import br.com.leonardo.dicionarioAPI.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email:String?): User?
}