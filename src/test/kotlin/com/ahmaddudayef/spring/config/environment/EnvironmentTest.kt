package com.ahmaddudayef.spring.config.environment

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment


@SpringBootTest(classes = [EnvironmentTest.TestApplication::class])
class EnvironmentTest {

    @Autowired
    private val environment: Environment? = null

    @Test
    fun testEnvironment() {
        val javaHome = environment?.getProperty("JAVA_HOME")
        Assertions.assertEquals("C:\\Program Files\\Zulu\\zulu-11", javaHome)
    }


    @SpringBootApplication
    class TestApplication
}