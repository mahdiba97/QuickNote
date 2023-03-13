package com.mahdiba97.quicknote.data.note

import com.mahdiba97.quicknote.database.QuickNoteDatabase
import com.mahdiba97.quicknote.domain.note.Note
import com.mahdiba97.quicknote.domain.note.NoteDataSource
import com.mahdiba97.quicknote.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: QuickNoteDatabase) : NoteDataSource {
    val queries = db.quicknoteQueries

    override suspend fun insertNote(note: Note) {
        with(note) {
            queries.insertNote(
                id = id,
                title = title,
                content = content,
                colorHex = colorHex,
                created = DateTimeUtil.toEpochMillis(created)
            )
        }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries.getNoteById(id).executeAsOneOrNull()?.toNote()
    }

    override suspend fun getAllNotes(): List<Note> {
        return queries.getAllNotes().executeAsList().map {
            it.toNote()
        }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}