package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.dto.MeaningView
import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class MeaningService {

    fun searchMeaning(word: String): List<MeaningView> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)
        val meanings: MutableList<MeaningView> = mutableListOf()
        val elements: Elements = doc.select("p.significado span")
        elements.forEach { element: Element ->
            val text: String = element.text()

            if (element.hasClass("cl")) {
                if (meanings.size == 1 && (meanings[0].partOfSpeech?.isEmpty() == true &&
                            meanings[0].meanings?.isEmpty() == true)
                ) {
                    meanings[0].partOfSpeech = text
                } else {
                    meanings.add(MeaningView(text, mutableListOf(), ""))
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



