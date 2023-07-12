package br.com.leonardo.dicionarioAPI.dto

data class MeaningView(
    var partOfSpeech: String? = null,
    val meanings: MutableList<String>? = null,
    var etymology: String? = null
)