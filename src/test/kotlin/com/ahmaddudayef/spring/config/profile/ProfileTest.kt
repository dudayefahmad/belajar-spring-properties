package com.ahmaddudayef.spring.config.profile

import com.ahmaddudayef.spring.config.profile.ProfileTest.TestApplication.SayHello
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.test.context.ActiveProfiles


@SpringBootTest(classes = [ProfileTest.TestApplication::class])
@ActiveProfiles("local")
class ProfileTest {

    @Autowired
    private var sayHello: SayHello? = null

    @Test
    fun testProfile() {
        Assertions.assertEquals("Hello Ahmad from Local", sayHello?.say("Ahmad"))
    }

    @SpringBootApplication
    class TestApplication {
        interface SayHello {
            fun say(name: String): String
        }

        @Component
        @Profile("local")
        class SayHelloLocal : SayHello {
            override fun say(name: String): String {
                return "Hello $name from Local"
            }
        }

        @Component
        @Profile("production")
        class SayHelloProduction : SayHello {
            override fun say(name: String): String {
                return "Hello $name from Production"
            }
        }
    }
}