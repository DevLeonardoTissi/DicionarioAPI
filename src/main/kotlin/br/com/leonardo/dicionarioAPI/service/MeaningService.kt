package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.controller.MeaningsResponse
import br.com.leonardo.dicionarioAPI.utils.connectsWithTheUrlAndReturnsTheDocument
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class MeaningService {

    fun searchMeaning(word: String): ArrayList<MeaningsResponse> {
        val doc: Document = connectsWithTheUrlAndReturnsTheDocument(word)

        val spansMeanings = doc.select("p.significado span:not(.etim, .cl)")
        val list: ArrayList<String> = ArrayList()

        for (span in spansMeanings) {
            val textsSpan = span.text()
            list.add(textsSpan)
        }

        val spanPartOfSpeech = doc.select("p.significado span.cl").text()
        val spanEtymology = doc.select("p.significado span.etim").text()

        val meaningsResponse: ArrayList<MeaningsResponse> = ArrayList()
        meaningsResponse.add(
            MeaningsResponse(
                partOfSpeech = spanPartOfSpeech,
                etymology = spanEtymology,
                meanings = list
            )
        )
        return meaningsResponse
    }
}