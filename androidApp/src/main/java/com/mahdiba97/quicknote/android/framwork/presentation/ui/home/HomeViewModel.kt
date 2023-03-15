package com.mahdiba97.quicknote.android.framwork.presentation.ui.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdiba97.quicknote.domain.note.Note
import com.mahdiba97.quicknote.domain.note.NoteDataSource
import com.mahdiba97.quicknote.domain.note.SearchNotes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val searchNote = SearchNotes()
    private val notes = savedStateHandle.getStateFlow(NOTE_KEY, emptyList<Note>())
    private val search = savedStateHandle.getStateFlow(SEARCH_KEY, "")
    private val isSearchActive = savedStateHandle.getStateFlow(IS_SEARCH_ACTIVE_KEY, false)

    val state = combine(
        notes, search, isSearchActive
    ) { notes, search, isSearchActive ->
        HomeViewState(searchNote.execute(notes, search), search, isSearchActive)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HomeViewState())

    //    init {
//        viewModelScope.launch {
//            repeat(20){
//                noteDataSource.insertNote(Note(
//                    id = it.toLong(),
//                    title = "Note Number $it",
//                    content = "This is my note content!",
//                    colorHex = Note.generateRandomColor(),
//                    created = DateTimeUtil.current()
//                ))
//            }
//        }
//    }
    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle[NOTE_KEY] = noteDataSource.getAllNotes()
        }
    }

    fun searchTextChange(value: String) {
        savedStateHandle[SEARCH_KEY] = value
    }

    fun onToggleSearch() {
        savedStateHandle[IS_SEARCH_ACTIVE_KEY] = !isSearchActive.value
        if (!isSearchActive.value) {
            savedStateHandle[SEARCH_KEY] = ""
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }

    companion object {
        const val NOTE_KEY = "notes"
        const val SEARCH_KEY = "search"
        const val IS_SEARCH_ACTIVE_KEY = "is_search_active"
    }
}