package com.mahdiba97.quicknote.domain.time

import kotlinx.datetime.*

object DateTimeUtil {
    fun current(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = with(dateTime.dayOfMonth) { if (this < 10) "0${this}" else this }
        val year = dateTime.year
        val hour = with(dateTime.hour) { if (this < 10) "0${this}" else this }
        val minute = with(dateTime.minute) { if (this < 10) "0${this}" else this }
        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(", ")
            append(hour)
            append(":")
            append(minute)
        }
    }
}