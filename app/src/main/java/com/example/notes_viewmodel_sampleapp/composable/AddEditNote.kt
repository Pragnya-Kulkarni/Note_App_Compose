package com.example.notes_viewmodel_sampleapp.composable

import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.notes_viewmodel_sampleapp.model.Note
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun AddEditNote(
    navController: NavHostController,
    addOrUpdateNotes: (Int, String, String) -> Unit,
    selectedNote: Note,
    isEdit: Boolean
) {

    var title by rememberSaveable { mutableStateOf(selectedNote.title) }
    var description by rememberSaveable {
        mutableStateOf(selectedNote.description)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Notes") },
                backgroundColor = MaterialTheme.colors.primary
            )
        },
        bottomBar = { BottomAppBar(backgroundColor = MaterialTheme.colors.primary) { Text("Bottom App Bar") } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { newText ->
                    title = newText
                }
            )
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { newText ->
                    description = newText
                }
            )
            Button(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                onClick = {
                    if (isEdit) {
                        selectedNote.title = title
                        selectedNote.description = description
                        addOrUpdateNotes(
                            selectedNote.id,
                            selectedNote.title,
                            selectedNote.description
                        )
                        navController.popBackStack()
                    } else {
                        selectedNote.title = title
                        selectedNote.description = description
                        addOrUpdateNotes(0, selectedNote.title, selectedNote.description)
                        navController.popBackStack()
                    }
                }
            ) {
                Text(text = "Save", textAlign = TextAlign.Center)
            }
        }
    }

}
