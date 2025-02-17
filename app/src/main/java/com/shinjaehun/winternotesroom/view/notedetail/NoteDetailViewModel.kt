package com.shinjaehun.winternotesroom.view.notedetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shinjaehun.winternotesroom.common.BaseViewModel
import com.shinjaehun.winternotesroom.common.GET_NOTE_ERROR
import com.shinjaehun.winternotesroom.common.Result
import com.shinjaehun.winternotesroom.common.currentTime
import com.shinjaehun.winternotesroom.model.INoteRepository
import com.shinjaehun.winternotesroom.model.Note
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val TAG = "NoteDetailViewModel"

class NoteDetailViewModel(
    val noteRepo: INoteRepository,
    uiContext: CoroutineContext
): BaseViewModel<NoteDetailEvent>(uiContext) {

    private val noteState = MutableLiveData<Note>()
    val note: LiveData<Note> get() = noteState

    private val noteImageState = MutableLiveData<ByteArray>()
    val noteImage: LiveData<ByteArray> get() = noteImageState

    private val noteImageDeletedState = MutableLiveData<Boolean>()
    val noteImageDeleted: LiveData<Boolean> get() = noteImageDeletedState

    private val noteColorState = MutableLiveData<String?>()
    val noteColor: LiveData<String?> get() = noteColorState

    private val webLinkState = MutableLiveData<String?>()
    val webLink: LiveData<String?> get() = webLinkState

    private val webLinkDeletedState = MutableLiveData<Boolean>()
    val webLinkDeleted: LiveData<Boolean> get() = webLinkDeletedState

    private val deletedState = MutableLiveData<Boolean>()
    val deleted: LiveData<Boolean> get() = deletedState

    private val updatedState = MutableLiveData<Boolean>()
    val updated: LiveData<Boolean> get() = updatedState

    var newNote: Note? = null

    override fun handleEvent(event: NoteDetailEvent) {
        when(event){
            is NoteDetailEvent.OnStart -> getNote(event.noteId)
            is NoteDetailEvent.OnDoneClick -> updateNote(event.note)
//            is NoteDetailEvent.OnNoteImageChange -> TODO()
//            is NoteDetailEvent.OnNoteImageDeleteClick -> TODO()
            is NoteDetailEvent.OnNoteColorChange -> changeNoteColor(event.color)
            is NoteDetailEvent.OnWebLinkChange -> changeWebLink(event.webLink)
            NoteDetailEvent.OnWebLinkDeleteClick -> deleteWebLink()
            NoteDetailEvent.OnDeleteClick -> deleteNote()
        }
    }

    private fun deleteNote() = launch {
        val deletedResult = noteRepo.deleteNote(note.value!!)
        when(deletedResult){
            is Result.Value -> deletedState.value = true
            is Result.Error -> deletedState.value = false
        }
    }

    private fun changeNoteColor(color: String?) {
        noteColorState.value = color
    }

    private fun changeWebLink(webLink: String?) {
        webLinkState.value = webLink
    }

    private fun deleteWebLink() {
        webLinkDeletedState.value = true
    }

    private fun updateNote(note: Note) = launch {
        val updateResult = noteRepo.insertOrUpdateNote(note)
        when(updateResult) {
            is Result.Value -> updatedState.value = true
            is Result.Error -> updatedState.value = false
        }
    }

    private fun getNote(noteId: String) = launch {
        if (noteId == "0") {
            newNote()
        } else {
            val noteResult = noteRepo.getNoteById(noteId)
            when (noteResult) {
                is Result.Value -> noteState.value = noteResult.value
                is Result.Error -> errorState.value = GET_NOTE_ERROR
            }
        }
    }

    private fun newNote() {
        noteState.value =
            Note("0", "", "", currentTime(), null, null, null)
    }

}