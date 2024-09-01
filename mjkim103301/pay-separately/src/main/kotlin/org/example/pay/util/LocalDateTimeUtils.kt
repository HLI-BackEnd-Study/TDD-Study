package org.example.pay.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class LocalDateTimeUtils {
    companion object {
        fun now(): LocalDateTime {
            return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        }
    }
}
