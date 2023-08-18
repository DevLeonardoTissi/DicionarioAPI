package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class SyllablesService {
    fun searchSyllables(word: String): List<String> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)
        val additionalElements: Elements = doc.select("p.adicional")
        val syllableValue = additionalElements.select("b")
            .firstOrNull { it.previousSibling()?.toString()?.trim() == "Separação silábica:" }
            ?.text()?.split("-")
        return syllableValue ?: emptyList()
    }
}
