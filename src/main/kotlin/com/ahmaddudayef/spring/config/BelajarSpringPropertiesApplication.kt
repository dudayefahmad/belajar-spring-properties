package com.ahmaddudayef.spring.config

import com.ahmaddudayef.spring.config.converter.StringToDateConverter
import com.ahmaddudayef.spring.config.properties.ApplicationProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.convert.ApplicationConversionService
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.core.convert.ConversionService


@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties::class)
class BelajarSpringPropertiesApplication {

	@Bean
	fun conversionService(stringToDateConverter: StringToDateConverter): ConversionService {
		val conversionService = ApplicationConversionService()
		conversionService.addConverter(stringToDateConverter)
		return conversionService
	}
}

fun main(args: Array<String>) {
	runApplication<BelajarSpringPropertiesApplication>(*args)
}
