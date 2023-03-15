package com.mahdiba97.quicknote.di

import com.mahdiba97.quicknote.data.local.DatabaseDriverFactory
import com.mahdiba97.quicknote.data.note.SqlDelightNoteDataSource
import com.mahdiba97.quicknote.database.QuickNoteDatabase
import com.mahdiba97.quicknote.domain.note.NoteDataSource

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(QuickNoteDatabase(factory.createDriver()))
    }
}