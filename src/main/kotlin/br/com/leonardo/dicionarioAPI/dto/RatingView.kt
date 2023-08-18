package br.com.leonardo.dicionarioAPI.dto

data class RatingView(
    val id:Long? = null,
    val userEmail:String,
    val rating: Float? = null,
    val comment:String? = null

)