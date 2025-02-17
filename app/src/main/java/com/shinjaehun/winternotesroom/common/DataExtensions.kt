package com.shinjaehun.winternotesroom.common

import android.text.Editable
import com.shinjaehun.winternotesroom.model.Note
import com.shinjaehun.winternotesroom.model.RoomNote

internal val RoomNote.toNote: Note
    get() = Note(
        this.noteId.toString(),
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath,
        this.color,
        this.webLink,
    )

internal val Note.toRoomNote: RoomNote
    get() = RoomNote(
        this.noteId.toInt(),
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath,
        this.color,
        this.webLink,
    )

internal fun List<RoomNote>.toNoteListFromRoomNoteList(): List<Note> = this.flatMap {
    listOf(it.toNote)
}

internal fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
