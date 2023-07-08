package br.com.leonardo.dicionarioAPI.controller

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.Exception

@RestController
@RequestMapping("/meaning")
class MeaningController {
    @GetMapping("/{word}")
    fun searchMeaning(@PathVariable word:String):String{
        return try {
            val url = "https://dicio.com.br/$word"

            val doc: Document = Jsoup.connect(url).get()
            val spans = doc.select("p.significado span")
            val list:ArrayList<String> = ArrayList()

            for (span in spans) {
                val textoSpan = span.text()
                list.add(textoSpan)
            }
            list.toString()
        }catch (e:Exception){
            "Erro"
        }

    }
}