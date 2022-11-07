package com.ahmaddudayef.spring.config.profileenvironment

import com.ahmaddudayef.spring.config.profileenvironment.ProfileEnvironmentTest.TestApplication.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.EnvironmentAware
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [ProfileEnvironmentTest.TestApplication::class])
@ActiveProfiles("production", "local")
class ProfileEnvironmentTest {

    @Autowired
    private val sampleProfile: SampleProfile? = null

    @Test
    fun testActiveProfiles() {
        Assertions.assertArrayEquals(arrayOf("production", "local"), sampleProfile?.profiles)
    }


    @SpringBootApplication
    class TestApplication {
        @Component
        class SampleProfile : EnvironmentAware {
            private lateinit var env: Environment

            val profiles: Array<String>
                get() = env.activeProfiles

            override fun setEnvironment(environment: Environment) {
                this.env = environment
            }
        }
    }
}