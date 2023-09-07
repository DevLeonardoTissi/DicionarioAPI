package br.com.leonardo.dicionarioAPI.dto

import java.io.Serializable
import java.sql.Timestamp

data class RatingView(
    val id: Long? = null,
    val userEmail: String,
    val rating: Float? = null,
    val comment: String? = null,
    val createdAt: Timestamp? = null

):Serializable