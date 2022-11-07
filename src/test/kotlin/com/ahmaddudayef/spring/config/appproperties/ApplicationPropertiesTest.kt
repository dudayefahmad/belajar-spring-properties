package com.ahmaddudayef.spring.config.appproperties

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment


@SpringBootTest(classes = [ApplicationPropertiesTest.TestApplication::class])
class ApplicationPropertiesTest {

    @Autowired
    private val environment: Environment? = null

    @Test
    fun testApplicationProperties() {
        val applicationName = environment?.getProperty("application.name")
        Assertions.assertEquals("Belajar Spring Boot", applicationName)
    }


    @SpringBootApplication
    class TestApplication
}