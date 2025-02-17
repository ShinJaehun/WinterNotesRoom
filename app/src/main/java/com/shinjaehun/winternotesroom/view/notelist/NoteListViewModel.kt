package com.shinjaehun.winternotesroom.view.notelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shinjaehun.winternotesroom.common.BaseViewModel
import com.shinjaehun.winternotesroom.common.GET_NOTES_ERROR
import com.shinjaehun.winternotesroom.common.Result
import com.shinjaehun.winternotesroom.common.currentTime
import com.shinjaehun.winternotesroom.model.INoteRepository
import com.shinjaehun.winternotesroom.model.Note
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val TAG = "NoteListViewModel"

class NoteListViewModel(
    private val noteRepo: INoteRepository,
    uiContext: CoroutineContext
): BaseViewModel<NoteListEvent>(uiContext) {

    private val noteListState = MutableLiveData<List<Note>>()
    val noteList: LiveData<List<Note>> get() = noteListState

    private val editNoteState = MutableLiveData<String>()
    val editNote: LiveData<String> get() = editNoteState

//    private val searchNoteListState = MutableLiveData<List<Note>>()
//    val searchNoteList: LiveData<List<Note>> get() = searchNoteListState

    override fun handleEvent(event: NoteListEvent) {
        when(event){
            is NoteListEvent.OnStart -> getNotes()
            is NoteListEvent.OnNoteItemClick -> editNote(event.position)
//            NoteListEvent.OnNewNoteClick -> newNote()
        }
    }

    private fun getNotes() = launch {
        val notesResult = noteRepo.getNotes()
        when (notesResult) {
            is Result.Value -> noteListState.value = notesResult.value
//            is Result.Value -> noteListState.value = notes
            is Result.Error -> errorState.value = GET_NOTES_ERROR
        }
    }

    private fun editNote(position: Int) {
        editNoteState.value = noteList.value!![position].noteId
    }

}

//private val notes = (1..30).map {
//    Note(
//        noteId = null,
//        title = "Title $it",
//        contents = "contents $it",
//        dateTime = currentTime(),
//        imagePath = null,
//        color = null,
//        webLink = null
//    )
//}