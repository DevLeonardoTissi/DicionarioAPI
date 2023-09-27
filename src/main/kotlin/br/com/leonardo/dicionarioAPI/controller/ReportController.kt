package br.com.leonardo.dicionarioAPI.controller

import br.com.leonardo.dicionarioAPI.service.RatingService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/rating/report")
@Suppress("unused")
class ReportController(private val ratingService: RatingService) {
    @GetMapping
    @Suppress("unused")
    fun report(model: Model): String {
        model.addAttribute("ratingsByUserEmail", ratingService.report())
        return "report"
    }

}