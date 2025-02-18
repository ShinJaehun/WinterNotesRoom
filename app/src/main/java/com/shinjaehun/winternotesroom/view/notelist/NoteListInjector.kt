package com.shinjaehun.winternotesroom.view.notelist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.shinjaehun.winternotesroom.common.ImageStorage
import com.shinjaehun.winternotesroom.model.INoteRepository
import com.shinjaehun.winternotesroom.model.NoteRepoImpl
import com.shinjaehun.winternotesroom.model.RoomNoteDatabase

class NoteListInjector(application: Application) : AndroidViewModel(application) {
    private fun getNoteRepository(): INoteRepository {
        return NoteRepoImpl(
            dao = RoomNoteDatabase.getInstance(getApplication()).roomNoteDao(),
            imageStorage = ImageStorage(context = getApplication()),
        )
    }

    fun provideNoteListViewModelFactory(): NoteListViewModelFactory =
        NoteListViewModelFactory(getNoteRepository())
}