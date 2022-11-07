package com.ahmaddudayef.spring.config.springbootmessagesource

import com.ahmaddudayef.spring.config.springbootmessagesource.SpringBootMessageSourceTest.TestApplication.SampleSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.context.MessageSourceAware
import org.springframework.stereotype.Component
import java.util.*

@SpringBootTest(classes = [SpringBootMessageSourceTest.TestApplication::class])
class SpringBootMessageSourceTest {

    @Autowired
    val sampleSource: SampleSource? = null

    @Test
    fun testHelloAhmad() {
        Assertions.assertEquals("Hello Ahmad", sampleSource?.helloAhmad(Locale.ENGLISH))
        Assertions.assertEquals("Halo Ahmad", sampleSource?.helloAhmad(Locale("in_ID")))
    }


    @SpringBootApplication
    class TestApplication {
        @Component
        class SampleSource : MessageSourceAware {

            lateinit var msgSource: MessageSource

            override fun setMessageSource(messageSource: MessageSource) {
                this.msgSource = messageSource
            }

            fun helloAhmad(locale: Locale): String {
                return msgSource.getMessage("hello", arrayOf<Any>("Ahmad"), locale)
            }


        }
    }

}