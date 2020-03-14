package com.azhara.noteapproom.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azhara.noteapproom.database.Note
import com.azhara.noteapproom.repository.NoteRepository

class MainViewModel(application: Application): ViewModel() {
    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun getAllNote(): LiveData<List<Note>> = mNoteRepository.getAllNote()
}