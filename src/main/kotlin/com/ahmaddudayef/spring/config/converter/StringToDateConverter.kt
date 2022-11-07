package com.ahmaddudayef.spring.config.converter

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.util.*


@Component
class StringToDateConverter : Converter<String, Date> {

    val DATE_FORMAT = SimpleDateFormat("yyyy-MM-dd")

    override fun convert(source: String): Date {
        return DATE_FORMAT.parse(source) ?: throw NullPointerException()
    }


}