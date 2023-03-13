package com.mahdiba97.quicknote.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.mahdiba97.quicknote.database.QuickNoteDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            QuickNoteDatabase.Schema, "quicknote.dp"
        )
    }
}