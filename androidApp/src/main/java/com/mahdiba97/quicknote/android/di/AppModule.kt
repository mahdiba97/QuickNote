package com.mahdiba97.quicknote.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.mahdiba97.quicknote.data.local.DatabaseDriverFactory
import com.mahdiba97.quicknote.data.note.SqlDelightNoteDataSource
import com.mahdiba97.quicknote.database.QuickNoteDatabase
import com.mahdiba97.quicknote.domain.note.NoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(QuickNoteDatabase(driver))
    }
}