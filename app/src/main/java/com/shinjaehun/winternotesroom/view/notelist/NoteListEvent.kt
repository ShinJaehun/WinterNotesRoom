package com.shinjaehun.winternotesroom.view.notelist

sealed class NoteListEvent {
    data object OnStart: NoteListEvent()
//    data object OnNewNoteClick: NoteListEvent()
    data class OnNoteItemClick(val position: Int): NoteListEvent()
//    data class OnSearchTextChange(val searchKeyword: String): NoteListEvent()
}