package com.shinjaehun.winternotesroom.view.notedetail

import com.shinjaehun.winternotesroom.model.Note

sealed class NoteDetailEvent {
    data class OnStart(val noteId: String): NoteDetailEvent()
    data class OnDoneClick(val note: Note): NoteDetailEvent()

//    data class OnNoteImageDeleteClick(val imagePath: String?): NoteDetailEvent()

    data class OnNoteColorChange(val color: String?): NoteDetailEvent()
    data class OnWebLinkChange(val webLink: String?): NoteDetailEvent()
    data object OnWebLinkDeleteClick: NoteDetailEvent()
    data object OnDeleteClick: NoteDetailEvent()

    data class OnNoteImagePick(val bytes: ByteArray): NoteDetailEvent()
    object OnAddNoteImageClick: NoteDetailEvent()
}