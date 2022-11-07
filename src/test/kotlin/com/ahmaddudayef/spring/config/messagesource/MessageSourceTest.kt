package com.ahmaddudayef.spring.config.messagesource

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*


class MessageSourceTest {

    private lateinit var applicationContext: ApplicationContext

    private lateinit var messageSource: MessageSource

    @BeforeEach
    fun setUp() {
        applicationContext = AnnotationConfigApplicationContext(TestApplication::class.java)
        messageSource = applicationContext.getBean(MessageSource::class.java)
    }

    @Test
    fun testDefaultLocale() {
        val message = messageSource.getMessage("hello", arrayOf<Any>("Ahmad"), Locale.ENGLISH)
        Assertions.assertEquals("Hello Ahmad", message)
    }

    @Test
    fun testIndonesianLocale() {
        val message = messageSource.getMessage("hello", arrayOf<Any>("Ahmad"), Locale("in_ID"))
        Assertions.assertEquals("Halo Ahmad", message)
    }

    @SpringBootApplication
    class TestApplication {
        @Bean
        fun messageSource(): MessageSource {
            val messageSource = ResourceBundleMessageSource()
            messageSource.setBasenames("my")
            return messageSource
        }
    }
}