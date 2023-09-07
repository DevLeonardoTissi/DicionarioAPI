package br.com.leonardo.dicionarioAPI.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(private val javaMailSender: JavaMailSender) {

    fun notify(author: String) {
        val message = SimpleMailMessage()
        message.subject = "[Dictionary API] Rating received "
        message.text = "Hello, your rating has been received. Thanks for contributing!"
        message.setTo(author)

        javaMailSender.send(message)

    }

}