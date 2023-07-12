package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class SynonymsService {
    fun searchSynonyms(word: String): List<String> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)
        val synonymsElements: Elements = doc.select(".sinonimos a")
        return synonymsElements.mapNotNull { it.text() }.toMutableList()
    }
}