package com.example.notes_viewmodel_sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    val notesViewModel = ViewModelProvider(this ).get(NoteViewModel::class.java)
                    // 2. to get navcontroller we have method called rememberNavController
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "list"){
                        composable("list"){
                            NotesScreen(navController = navController,notesViewModel.notesList, notesViewModel::setSelectedNote,this@MainActivity )
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

