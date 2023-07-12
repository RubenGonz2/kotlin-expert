package com.devexperto.kotlinexpert.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.devexperto.kotlinexpert.data.Note
import com.devexperto.kotlinexpert.ui.viewmodels.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun Home(vm: HomeViewModel, onNoteClick: (Long) -> Unit) {

    MaterialTheme {
        Scaffold(
            topBar = { TopBar(onFilterClick = vm::onFilterClick) },
            floatingActionButton = {
                FloatingActionButton(onClick = { onNoteClick(Note.NEW_NOTE) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
                }
            }
        ) { padding ->
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(padding).fillMaxSize()
            ) {
                if (vm.state.loading) {
                    CircularProgressIndicator()
                }

                vm.state.filteredNotes?.let { notes ->
                    NotesList(
                        notes = notes,
                        onNoteClick = { onNoteClick(it.id) }
                    )
                }
            }
        }
    }
}