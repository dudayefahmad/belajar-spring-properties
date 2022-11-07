package com.ahmaddudayef.spring.config.propertysource

import com.ahmaddudayef.spring.config.propertysource.PropertySourceTest.TestApplication.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.PropertySource
import org.springframework.context.annotation.PropertySources
import org.springframework.stereotype.Component


@SpringBootTest(classes = [PropertySourceTest.TestApplication::class])
class PropertySourceTest {

    @Autowired
    private val properties: SampleProperties? = null

    @Test
    fun testPropertySource() {
        Assertions.assertEquals("Sample Project", properties?.name)
        Assertions.assertEquals(1, properties?.version)
    }

    @SpringBootApplication
    @PropertySources(
        PropertySource("classpath:/sample.properties")
    )
    class TestApplication {
        @Component
        data class SampleProperties(
            @Value("\${sample.name}") val name: String,
            @Value("\${sample.version}") val version: Int
        )
    }

}