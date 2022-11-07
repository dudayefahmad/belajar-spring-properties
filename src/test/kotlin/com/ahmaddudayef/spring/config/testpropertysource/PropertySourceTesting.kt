package com.ahmaddudayef.spring.config.testpropertysource

import com.ahmaddudayef.spring.config.testpropertysource.PropertySourceTesting.TestApplication.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.TestPropertySources


@TestPropertySources(TestPropertySource("classpath:/test.properties"))
@SpringBootTest(classes = [PropertySourceTesting.TestApplication::class])
class PropertySourceTesting {

    @Autowired
    private val properties: SampleProperties? = null

    @Test
    fun testPropertySource() {
        Assertions.assertEquals("Sample Project Test", properties?.name)
        Assertions.assertEquals(1, properties?.version)
    }

    @SpringBootApplication
    class TestApplication {

        @Component
        data class SampleProperties(
            @Value("\${sample.name}") val name: String,
            @Value("\${sample.version}") val version: Int
        )
    }
}