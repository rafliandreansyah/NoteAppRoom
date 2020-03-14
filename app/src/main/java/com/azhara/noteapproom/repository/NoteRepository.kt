package com.azhara.noteapproom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.azhara.noteapproom.database.Note
import com.azhara.noteapproom.database.NoteDao
import com.azhara.noteapproom.database.NoteRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {
    private val mNoteDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNoteDao = db.noteDao()
    }

    fun getAllNote(): LiveData<List<Note>> = mNoteDao.getAllNotes()

    fun insert(note: Note){
        executorService.execute { mNoteDao.insert(note) }
    }

    fun update(note: Note){
        executorService.execute { mNoteDao.update(note) }
    }

    fun delete(note: Note){
        executorService.execute{ mNoteDao.delete(note) }
    }
}