package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.dto.SentenceView
import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class SentencesService {
    fun searchSentences(word: String): List<SentenceView> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)
        val sentencesElements: Elements = doc.select(".frase span ")

        return sentencesElements.map { element: Element ->
            val sentence = element.ownText().trim()
            val author = element.selectFirst("em")?.text()?.replace("-", "")?.trim()
            SentenceView(sentence = sentence, author = author)
        }
    }
}