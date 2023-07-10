package br.com.leonardo.dicionarioAPI.utils

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

fun connectsWithTheUrlAndReturnsTheDocument(word: String): Document {
    val url = "$BASE_URL${returnWordWithoutSpecialCharacters(word)}"
    return Jsoup.connect(url).get()
}