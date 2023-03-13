package com.mahdiba97.quicknote.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.mahdiba97.quicknote.database.QuickNoteDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            QuickNoteDatabase.Schema, context, "quicknote.dp"
        )
    }
}