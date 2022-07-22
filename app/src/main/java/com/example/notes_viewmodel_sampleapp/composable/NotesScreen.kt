package com.example.notes_viewmodel_sampleapp.composable

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import com.example.notes_viewmodel_sampleapp.model.Note


@Composable
fun NotesScreen(
    navController: NavHostController,
    lstNotes: LiveData<List<Note>>,
    selectNote: (Note) -> Unit,
    activity: ComponentActivity
) {
    val context= LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectNote(Note(0, "", ""))
                navController.navigate("add")
            }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            lstNotes.observe(activity ) {
                    it.forEach{ note->
                        item {
                            DetailCard(note=note, navController = navController, selectNote)
                        }
                    }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun DetailCard(note: Note, navController: NavHostController, selectNote: (Note) -> Unit) {
    Card(
        modifier = Modifier.fillMaxSize(),
        elevation = 5.dp,
        onClick = {
            selectNote(note)
            navController.navigate("edit")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = note.title)
                Text(text = note.description)
            }
        }

    }

}