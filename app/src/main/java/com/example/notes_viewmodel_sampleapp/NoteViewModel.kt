package com.example.notes_viewmodel_sampleapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.notes_viewmodel_sampleapp.model.Note

class NoteViewModel : ViewModel() {


    private val _notesList = getNotes().toMutableStateList()
    val notesList: List<Note>
        get() = _notesList

    private lateinit var _selectedNote: Note
    val selectedNote: Note
        get() {
            return _selectedNote
        }

    fun setSelectedNote(selectedNote: Note) {
        _selectedNote = selectedNote
    }

    private fun getNotes(): List<Note> {
        return listOf(
            Note(
                1, "Note1",
                "This is about note one description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                2, "Note2",
                "This is about note two description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                3, "Note3",
                "This is about note three description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                4, "Note4",
                "This is about note four description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                5, "Note5",
                "This is about note five description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                6, "Note6",
                "This is about note six description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                7, "Note7",
                "This is about note seven description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                8, "Note8",
                "This is about note eight description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                9, "Note9",
                "This is about note nine description, you can edit it by click on edit button, delete it by clicking on delete button"
            ),
            Note(
                10, "Note10",
                "This is about note ten description, you can edit it by click on edit button, delete it by clicking on delete button"
            )
        )
    }


    fun addOrUpdateNotes(id: Int, title: String, description: String) {
        if (id > 0) {
            val noteToEdit = _notesList.find { note -> note.id == id }
            noteToEdit?.let {
                it.title = title
                it.description = description
            }
        } else {
            var count = 1
            if (_notesList.count() > 0)
                count = _notesList.count() + 1

            _notesList.add(Note(count, title = title, description = description))
        }
    }

    fun deleteNotes(note: Note) {
        _notesList?.remove(note)
    }
}