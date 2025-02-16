package com.shinjaehun.winternotesroom.common

import android.text.Editable
import com.shinjaehun.winternotesroom.model.NoteEntity
import com.shinjaehun.winternotesroom.model.RoomNote

internal val RoomNote.toNoteEntity: NoteEntity
    get() = NoteEntity(
        this.noteId.toString(),
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath,
        this.color,
        this.webLink,
    )

internal val NoteEntity.toRoomNote: RoomNote
    get() = RoomNote(
        this.noteId!!.toInt(),
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath,
        this.color,
        this.webLink,
    )

internal fun List<RoomNote>.toNoteListFromRoomNoteList(): List<NoteEntity> = this.flatMap {
    listOf(it.toNoteEntity)
}

internal fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
