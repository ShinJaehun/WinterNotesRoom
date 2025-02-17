package com.shinjaehun.winternotesroom.view.notedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shinjaehun.winternotesroom.model.INoteRepository
import kotlinx.coroutines.Dispatchers

class NoteDetailViewModelFactory(
    private val noteRepo: INoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteDetailViewModel(noteRepo, Dispatchers.Main) as T
    }
}