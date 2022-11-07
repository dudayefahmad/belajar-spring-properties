package com.ahmaddudayef.spring.config.runner

import com.ahmaddudayef.spring.config.properties.ApplicationProperties
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class ApplicationPropertiesRunner : ApplicationRunner {

    @Autowired
    private val properties: ApplicationProperties? = null

    override fun run(args: ApplicationArguments?) {
        logger.info(properties?.name)
        logger.info(properties?.version.toString())
        logger.info(properties?.productionMode.toString())
    }
}