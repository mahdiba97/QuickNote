package com.mahdiba97.quicknote.domain.note

import com.mahdiba97.quicknote.presentation.*
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val created: LocalDateTime
) {
    companion object {
        private val colors = listOf(
            Blue30,
            Blue40,
            BlueGrey30,
            BlueGrey40,
            Violet30,
            Violet40,
            Red30,
            Red40
        )

        fun generateRandomColor() = colors.random()
    }
}
