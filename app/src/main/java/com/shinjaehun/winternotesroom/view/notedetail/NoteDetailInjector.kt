package com.shinjaehun.winternotesroom.view.notedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shinjaehun.winternotesroom.model.INoteRepository
import com.shinjaehun.winternotesroom.model.NoteRepoImpl
import com.shinjaehun.winternotesroom.model.RoomNoteDatabase

class NoteDetailInjector(application: Application): AndroidViewModel(application) {
    private fun getNoteRepository(): INoteRepository {
        return NoteRepoImpl(
            dao = RoomNoteDatabase.getInstance(getApplication()).roomNoteDao()
        )
    }

    fun provideNoteDetailViewModelFactory(): NoteDetailViewModelFactory =
        NoteDetailViewModelFactory(getNoteRepository())
}