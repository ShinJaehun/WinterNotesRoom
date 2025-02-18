package com.shinjaehun.winternotesroom.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import com.shinjaehun.winternotesroom.model.Note
import com.shinjaehun.winternotesroom.model.RoomNote

suspend fun RoomNote.toNote(imageStorage: ImageStorage): Note {
    return Note(
        this.noteId.toString(),
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath?.let { imageStorage.getImage(it) },
        this.color,
        this.webLink,
    )
}

fun Note.toRoomNote(imagePath: String?): RoomNote {
    return RoomNote(
        this.noteId.toInt(),
        this.title,
        this.contents,
        this.dateTime,
        imagePath,
        this.color,
        this.webLink,
    )
}

internal fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)

fun getBitmapFromBytes(bytes: ByteArray?): Bitmap? {
    return if (bytes != null) {
        BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    } else {
        null
    }
}