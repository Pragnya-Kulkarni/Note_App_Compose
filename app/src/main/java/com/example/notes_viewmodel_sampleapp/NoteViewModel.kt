package com.example.notes_viewmodel_sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes_viewmodel_sampleapp.model.Note

class NoteViewModel : ViewModel() {

    var listOfNotes: MutableList<Note>
    private var _notesList = MutableLiveData<List<Note>>()
    val notesList: LiveData<List<Note>>
        get() = _notesList

    init {
        _notesList.value = getNotes()
        listOfNotes = getNotes().toMutableList()
       // setSelectedNote(Note(0, "", ""))
    }

    private lateinit var _selectedNote: Note
    val selectedNote: Note
        get() {
            return _selectedNote
        }

    fun setSelectedNote(selectedNote: Note) {
        _selectedNote = selectedNote
    }

    fun getNotes(): List<Note> {
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
            notesList.value?.let {
              val note= it.find { note-> note.id== id }
                note?.let {
                    note?.title= title
                    note?.description= description
                }
                _notesList.postValue(listOfNotes)
            }
        } else {
            var count = 1
            notesList.value?.let {
                count = it.count()+1
            }
            listOfNotes?.add(Note(count, title = title, description = description))
            _notesList.postValue(listOfNotes)

        }
    }

    fun deleteNotes(note: Note) {
        val note = listOfNotes.find { n -> n.id == note.id }
        listOfNotes?.remove(note)
        _notesList.value = listOfNotes
    }
}