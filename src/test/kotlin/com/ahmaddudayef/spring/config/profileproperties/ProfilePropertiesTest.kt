package com.ahmaddudayef.spring.config.profileproperties

import com.ahmaddudayef.spring.config.profileproperties.ProfilePropertiesTest.TestApplication.ProfileProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Component
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [ProfilePropertiesTest.TestApplication::class])
@ActiveProfiles("production", "test")
class ProfilePropertiesTest {

    @Autowired
    private lateinit var properties: ProfileProperties

    @Test
    fun nameProfileProperties() {
        Assertions.assertEquals("Default", properties.defaultFile)
        Assertions.assertEquals("Production", properties.productionFile)
        Assertions.assertEquals("Test", properties.testFile)
    }

    @SpringBootApplication
    class TestApplication {

        @Component
        data class ProfileProperties(
            @Value("\${profile.default}") val defaultFile: String? = null,
            @Value("\${profile.production}") val productionFile: String? = null,
            @Value("\${profile.test}") val testFile: String? = null
        )
    }

}