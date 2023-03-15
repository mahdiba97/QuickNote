package com.mahdiba97.quicknote.android.framwork.presentation.ui.home

import com.mahdiba97.quicknote.domain.note.Note

data class HomeViewState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false,

    )
