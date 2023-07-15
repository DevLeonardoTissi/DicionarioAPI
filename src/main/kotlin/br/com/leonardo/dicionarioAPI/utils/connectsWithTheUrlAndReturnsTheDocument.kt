package br.com.leonardo.dicionarioAPI.utils

import br.com.leonardo.dicionarioAPI.exception.NotFoundException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun connectsWithTheUrlAndReturnsTheDocument(word: String): Document {
    try {
        val url = "$BASE_URL${returnWordWithoutSpecialCharacters(word)}"
        return Jsoup.connect(url).get()
    } catch (e:Exception){
        throw NotFoundException(NOT_FOUND_EXCEPTION_MESSAGE)
    }
}