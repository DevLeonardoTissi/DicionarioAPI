package br.com.example.dicionarioAPI.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/meaning")
class MeaningController {
    @GetMapping("/{word}")
    fun searchMeaning(@PathVariable word:String):String{
        return word
    }
}