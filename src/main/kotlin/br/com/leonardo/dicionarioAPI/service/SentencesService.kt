package br.com.leonardo.dicionarioAPI.service

import br.com.leonardo.dicionarioAPI.dto.SentenceView
import org.springframework.stereotype.Service

@Service
class SentencesService {
    fun searchSentences(word: String): List<SentenceView> {
        return mutableListOf()
    }
}