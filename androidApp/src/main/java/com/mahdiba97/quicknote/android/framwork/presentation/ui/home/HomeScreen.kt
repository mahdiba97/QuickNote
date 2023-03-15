package com.mahdiba97.quicknote.android.framwork.presentation.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }

    Scaffold(Modifier.fillMaxSize(), topBar = {
        AnimatedVisibility(
            visible = !state.isSearchActive,
            enter = fadeIn(),
        ) {
            TopAppBar(
                title = { Text(text = "QuickNote") },
                modifier = Modifier,
                actions = {
                    IconButton(onClick = viewModel::onToggleSearch) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = null)
                    }
                },
            )
        }
    }, floatingActionButton = {
        FloatingActionButton(onClick = { navController.navigate("detail/-1L") }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            item {
                if (state.isSearchActive)
                    SearchTextField(
                        text = state.searchText,
                        isSearchActive = state.isSearchActive,
                        onTextChange = viewModel::searchTextChange,
                        onSearchClick = viewModel::onToggleSearch,
                        onCloseClick = viewModel::onToggleSearch
                    )
            }
            items(state.notes) {
                NoteItemView(
                    note = it,
                    backgroundColor = Color(it.colorHex),
                    onNoteClick = { navController.navigate("detail/${it.id}") },
                    onDeleteClick = { viewModel.deleteNote(it.id ?: -1) },
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}