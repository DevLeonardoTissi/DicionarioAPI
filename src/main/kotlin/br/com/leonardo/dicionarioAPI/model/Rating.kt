package br.com.leonardo.dicionarioAPI.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.CreationTimestamp
import java.sql.Timestamp

@Entity
data class Rating(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val userEmail: String,
    val rating: Float? = null,
    val comment: String? = null,
    @CreationTimestamp
    val createdAt: Timestamp? = null
)