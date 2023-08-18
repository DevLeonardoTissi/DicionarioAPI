package br.com.leonardo.dicionarioAPI.utils

import java.util.*

fun returnWordWithoutSpecialCharacters(word: String): String {
    val wordLowerCaseWithoutSpaces = word.lowercase(Locale.getDefault()).trim()
    val charactersSpecialsMap = mapOf(
        "ç" to "c",
        "á" to "a",
        "é" to "e",
        "í" to "i",
        "ó" to "o",
        "ú" to "u",
        "ã" to "a",
        "õ" to "o",
        "â" to "a",
        "ê" to "e",
        "ô" to "o",
        "ü" to "u",
        "à" to "a",
    )

    var wordWithOutSpecialCharacters = wordLowerCaseWithoutSpaces
    for ((specialCharacters, replacement) in charactersSpecialsMap) {
        wordWithOutSpecialCharacters = wordWithOutSpecialCharacters.replace(specialCharacters, replacement)
    }
    return wordWithOutSpecialCharacters
}