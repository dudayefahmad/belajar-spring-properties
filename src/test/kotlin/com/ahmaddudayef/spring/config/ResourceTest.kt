package com.ahmaddudayef.spring.config

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.core.io.ClassPathResource


class ResourceTest {

    @Test
    fun testResource() {
        val resource = ClassPathResource("/text/sample.txt")
        Assertions.assertNotNull(resource)
        Assertions.assertTrue(resource.exists())
        Assertions.assertNotNull(resource.file)
    }
}