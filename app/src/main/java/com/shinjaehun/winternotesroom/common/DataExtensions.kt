package com.shinjaehun.winternotesroom.common

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.text.Editable
import android.util.Log
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmap
import com.shinjaehun.winternotesroom.model.Note
import com.shinjaehun.winternotesroom.model.RoomNote
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import kotlin.math.ln


private const val TAG = "DataExtensions"

suspend fun RoomNote.toNote(imageStorage: ImageStorage): Note {
    return Note(
//        this.noteId.toString(),
        this.noteId,
        this.title,
        this.contents,
        this.dateTime,
        this.imagePath?.let { imageStorage.getImage(it) },
        this.color,
        this.webLink,
    )
}

fun Note.toRoomNote(imagePath: String?): RoomNote {
//    Log.i(TAG, "note: $this")
//    Log.i(TAG, "imagePath: $imagePath")
    return RoomNote(
//        this.noteId.toLong(),
        this.noteId,
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

//fun getByteArrayFromImageView(imageView: ImageView): ByteArray? {
//    try {
//        val bm = imageView.drawable.toBitmap()
//        val stream = ByteArrayOutputStream()
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//        이것 때문에 이미지를 저장할 때마다 달라지는 것 같다.
//        val bytesData = stream.toByteArray()
//        stream.close()
//        return bytesData
//    } catch(e: Exception) {
//        e.printStackTrace()
//        return null
//    }
//}