package com.ahmaddudayef.spring.config.resourceloader

import com.ahmaddudayef.spring.config.resourceloader.ResourceLoaderTest.TestApplication.SampleResource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ResourceLoaderAware
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Component


@SpringBootTest(classes = [ResourceLoaderTest.TestApplication::class])
class ResourceLoaderTest {

    @Autowired
    private lateinit var sampleResource: SampleResource

    @Test
    @Throws(Exception::class)
    fun testResourceLoader() {
        Assertions.assertEquals("Ahmad Dudayef", sampleResource.getText().trim());
    }

    @SpringBootApplication
    class TestApplication {

        @Component
        class SampleResource : ResourceLoaderAware {

            private lateinit var resourceLoader: ResourceLoader

            override fun setResourceLoader(resourceLoader: ResourceLoader) {
                this.resourceLoader = resourceLoader
            }

            @Throws(Exception::class)
            fun getText(): String {
                val resource = resourceLoader.getResource("classpath:/text/resource.txt")
                resource.inputStream.use { inputStream ->
                    return String(inputStream.readAllBytes())
                }
            }
        }
    }
}