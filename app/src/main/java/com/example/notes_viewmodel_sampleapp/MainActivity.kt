package com.example.notes_viewmodel_sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes_viewmodel_sampleapp.composable.AddEditNote
import com.example.notes_viewmodel_sampleapp.composable.NotesScreen
import com.example.notes_viewmodel_sampleapp.ui.theme.Notes_ViewModel_SampleAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Notes_ViewModel_SampleAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("Notes App") },
                            backgroundColor = MaterialTheme.colors.primary
                        )
                    },
                ) {
                    val notesViewModel = ViewModelProvider(this ).get(NoteViewModel::class.java)
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "list"){
                        composable("list"){
                            NotesScreen(navController = navController,notesViewModel.notesList, notesViewModel::setSelectedNote,notesViewModel::deleteNotes)
                        }
                        composable("edit"){
                            AddEditNote(navController = navController,notesViewModel::addOrUpdateNotes, notesViewModel.selectedNote, true)
                        }
                        composable("add"){
                            AddEditNote(navController = navController,notesViewModel::addOrUpdateNotes, notesViewModel.selectedNote, false)
                        }
                    }

                }
            }
        }
    }
}

