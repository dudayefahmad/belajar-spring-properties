package com.ahmaddudayef.spring.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration
import java.util.Date

@ConstructorBinding
@ConfigurationProperties("application")
data class ApplicationProperties(
    val expireDate: Date,
    val defaultTimeout: Duration,
    val name: String,
    val version: Int,
    val productionMode: Boolean,
    val database: DatabaseProperties,
    val defaultRoles: List<Role>,
    val roles: Map<String, Role>
) {
    data class DatabaseProperties(
        val username: String,
        val password: String,
        val url: String,
        val database: String,
        val whitelistTables: List<String>,
        val maxTablesSize: Map<String, Int>
    )

    data class Role(
        val id: String,
        val name: String
    )
}