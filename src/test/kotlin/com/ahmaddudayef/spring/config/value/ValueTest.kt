package com.ahmaddudayef.spring.config.value

import com.ahmaddudayef.spring.config.value.ValueTest.TestApplication.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component


@SpringBootTest(classes = [ValueTest.TestApplication::class])
class ValueTest {

    @Autowired
    private lateinit var properties: ApplicationProperties

    @Autowired
    private lateinit var systemProperties: SystemProperties

    @Test
    fun testValue() {
        Assertions.assertEquals("Belajar Spring Boot", properties.name)
        Assertions.assertEquals(1, properties.version)
        Assertions.assertEquals(false, properties.productionMode);
    }

    @Test
    fun testSystemProperties() {
        Assertions.assertEquals("C:\\Program Files\\Zulu\\zulu-11", systemProperties.javaHome)
    }

    @SpringBootApplication
    class TestApplication {

        @Component
        data class SystemProperties(
            @Value("\${JAVA_HOME}")
            val javaHome: String
        )

        @Component
        data class ApplicationProperties(
            @Value("\${application.name}")
            val name: String,
            @Value("\${application.version}")
            val version: Int,
            @Value("\${application.production-mode}")
            val productionMode: Boolean
        )
    }
}