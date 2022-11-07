package com.ahmaddudayef.spring.config.configurationproperties

import com.ahmaddudayef.spring.config.converter.StringToDateConverter
import com.ahmaddudayef.spring.config.properties.ApplicationProperties
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.convert.ApplicationConversionService
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.core.convert.ConversionService
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*


@SpringBootTest(classes = [ConfigurationPropertiesTest.TestApplication::class])
class ConfigurationPropertiesTest {

    @Autowired
    private lateinit var properties: ApplicationProperties

    @Autowired
    private lateinit var conversionService: ConversionService

    @Test
    fun testConversionService() {
        Assertions.assertTrue(conversionService.canConvert(String::class.java, Duration::class.java))
        Assertions.assertTrue(conversionService.canConvert(String::class.java, Date::class.java))
        val result = conversionService.convert("10s", Duration::class.java)
        Assertions.assertEquals(Duration.ofSeconds(10), result)
    }

    @Test
    fun testConfigurationProperties() {
        Assertions.assertEquals("Belajar Spring Boot", properties.name)
        Assertions.assertEquals(1, properties.version)
        Assertions.assertEquals(false, properties.productionMode)
    }

    @Test
    fun testDatabaseProperties() {
        Assertions.assertEquals("ahmad", properties.database.username)
        Assertions.assertEquals("rahasia", properties.database.password)
        Assertions.assertEquals("belajar", properties.database.database)
        Assertions.assertEquals("jdbc:contoh", properties.database.url)
    }

    @Test
    fun testCollection() {
        Assertions.assertEquals(
            listOf("products", "customers", "categories"),
            properties.database.whitelistTables
        )
        Assertions.assertEquals(100, properties.database.maxTablesSize["products"])
        Assertions.assertEquals(100, properties.database.maxTablesSize["categories"])
        Assertions.assertEquals(100, properties.database.maxTablesSize["customers"])
    }

    @Test
    fun testEmbeddedCollection() {
        Assertions.assertEquals("default", properties.defaultRoles[0].id)
        Assertions.assertEquals("Default Role", properties.defaultRoles[0].name)
        Assertions.assertEquals("guest", properties.defaultRoles[1].id)
        Assertions.assertEquals("Guest Role", properties.defaultRoles[1].name)
        Assertions.assertEquals("admin", properties.roles["admin"]?.id)
        Assertions.assertEquals("Admin Role", properties.roles["admin"]?.name)
        Assertions.assertEquals("finance", properties.roles["finance"]?.id)
        Assertions.assertEquals("Finance Role", properties.roles["finance"]?.name)
    }

    @Test
    fun testDuration() {
        Assertions.assertEquals(Duration.ofSeconds(10), properties.defaultTimeout)
    }

    @Test
    fun testCustomConverter() {
        val expireDate: Date = properties.expireDate!!
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        Assertions.assertEquals("2022-11-04", dateFormat.format(expireDate))
    }

    @SpringBootApplication
    @EnableConfigurationProperties(ApplicationProperties::class)
    @Import(StringToDateConverter::class)
    class TestApplication {

        @Bean
        fun conversionService(stringToDateConverter: StringToDateConverter): ConversionService {
            val conversionService = ApplicationConversionService()
            conversionService.addConverter(stringToDateConverter)
            return conversionService
        }

    }
}