package com.shinjaehun.winternotesroom.model

import android.util.Log
import com.shinjaehun.winternotesroom.common.ImageStorage
import com.shinjaehun.winternotesroom.common.Result
import com.shinjaehun.winternotesroom.common.toNote
import com.shinjaehun.winternotesroom.common.toRoomNote

private const val TAG = "NoteRepoImpl"

class NoteRepoImpl(
    private val dao: NoteDao,
    private val imageStorage: ImageStorage
): INoteRepository {
    override suspend fun getNotes(): Result<Exception, List<Note>> {
        return getLocalNotes()
    }

    override suspend fun getNoteById(noteId: Long): Result<Exception, Note> {
        return getLocalNote(noteId)
    }

    override suspend fun deleteNote(noteId: Long): Result<Exception, Unit> {
        return deleteLocalNote(noteId)
    }

    override suspend fun insertOrUpdateNote(note: Note): Result<Exception, Unit> {
        return insertOrUpdateLocalNote(note)
    }

    private suspend fun getLocalNotes(): Result<Exception, List<Note>> = Result.build {
        dao.getNotes()
            .map { roomNote ->
                roomNote.toNote(imageStorage)
            }
    }

    private suspend fun getLocalNote(noteId: Long): Result<Exception, Note> = Result.build {
        dao.getNoteById(noteId).toNote(imageStorage)
    }

    private suspend fun deleteLocalNote(noteId: Long): Result<Exception, Unit> = Result.build {
        val roomNote = dao.getNoteById(noteId)
        roomNote.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        dao.deleteNote(noteId)
    }

    private suspend fun insertOrUpdateLocalNote(note: Note): Result<Exception, Unit> = Result.build {
        val imagePath: String?
        if (note.noteId == 0.toLong()) {
            if (note.imageBytes != null) {
                Log.i(TAG, "new image!")
                imagePath = note.imageBytes.let {
                    imageStorage.saveImage(it)
                }
            } else {
                Log.i(TAG, "no image!")
                imagePath = null
            }
        } else {
            // 이미지를 비교하지 않고 매번 갱신하는 방법이 나을 수 있음...
//            val beforeUpdateRoomNote = note.noteId.let { dao.getNoteById(it) }
//            Log.i(TAG, "different image!")
//            beforeUpdateRoomNote.imagePath?.let {
//                imageStorage.deleteImage(it)
//            }
//            imagePath = note.imageBytes?.let {
//                imageStorage.saveImage(it)
//            }

            val beforeUpdateRoomNote = note.noteId.let { dao.getNoteById(it) }
            val beforeUpdateRoomNoteImageBytes = beforeUpdateRoomNote.imagePath?.let {
                imageStorage.getImage(it)
            }
            val updateNoteImageBytes = note.imageBytes
            val isSameImage =
                beforeUpdateRoomNoteImageBytes != null &&
                        updateNoteImageBytes != null &&
                        beforeUpdateRoomNoteImageBytes.contentEquals(updateNoteImageBytes)
            // view에서 bitmap 이미지를 받아오는데
            // 압축 과정이 있기 때문에 항상 이미지가 달라지는 것 같다.
            if (isSameImage) {
                Log.i(TAG, "same image!")
                imagePath = beforeUpdateRoomNote.imagePath
            } else {
                Log.i(TAG, "different image!")
                beforeUpdateRoomNote.imagePath?.let {
                    imageStorage.deleteImage(it)
                }
                imagePath = note.imageBytes?.let {
                    imageStorage.saveImage(it)
                }
            }
        }
        Log.i(TAG, "new imagePath: $imagePath")

        dao.insertOrUpdateNote(note.toRoomNote(imagePath))
    }
}