package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.controller.MeaningsResponse
import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import org.jsoup.select.Elements

@Service
class MeaningService {

    fun searchMeaning(word: String): List<MeaningsResponse> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)
        val meanings: MutableList<MeaningsResponse> = mutableListOf()
        val elements: Elements = doc.select("p.significado span")
        elements.forEach { element: Element ->
            val text: String = element.text()

            if (element.hasClass("cl")) {
                if (meanings.size == 1 && (meanings[0].partOfSpeech?.isEmpty() == true &&
                            meanings[0].meanings?.isEmpty() == true)
                ) {
                    meanings[0].partOfSpeech = text
                } else {
                    meanings.add(MeaningsResponse(text, mutableListOf(), ""))
                }
            } else if (element.hasClass("etim")) {
                meanings[meanings.size - 1].etymology = text
            } else if (!element.hasClass("tag")) {
                meanings[meanings.size - 1].meanings?.add(text)
            }
        }
        return meanings
    }
}



