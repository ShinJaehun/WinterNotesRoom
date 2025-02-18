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

    override suspend fun getNoteById(noteId: String): Result<Exception, Note> {
        return getLocalNote(noteId)
    }

    override suspend fun deleteNote(noteId: String): Result<Exception, Unit> {
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

    private suspend fun getLocalNote(noteId: String): Result<Exception, Note> = Result.build {
        dao.getNoteById(noteId).toNote(imageStorage)
    }

    private suspend fun deleteLocalNote(noteId: String): Result<Exception, Unit> = Result.build {
        val roomNote = dao.getNoteById(noteId)
        roomNote.imagePath?.let {
            imageStorage.deleteImage(it)
        }
        dao.deleteNote(noteId)
    }

    private suspend fun insertOrUpdateLocalNote(note: Note): Result<Exception, Unit> = Result.build {
        // ImageStorage에서 null 처리를 하는게 맞지 않을까?
        Log.i(TAG, "insertOrUpdateLocalNote note: $note")

        val beforeUpdateRoomNote = note.noteId.let { dao.getNoteById(it) }
        Log.i(TAG, "beforeUpdateNote: $beforeUpdateRoomNote")

        val beforeUpdateRoomNoteBytes = beforeUpdateRoomNote.imagePath?.let {
            imageStorage.getImage(it)
        }
        Log.i(TAG, "beforeUpdateNoteBytes: ${beforeUpdateRoomNoteBytes.toString()}")

        val updateNoteBytes = note.imageBytes
        Log.i(TAG, "updateNoteBytes: ${updateNoteBytes.toString()}")

        val isSameImage = beforeUpdateRoomNoteBytes != null && updateNoteBytes != null &&
                beforeUpdateRoomNoteBytes.contentEquals(updateNoteBytes)
        Log.i(TAG, "isSameImage: $isSameImage")

        val imagePath: String? = if (isSameImage) {
            beforeUpdateRoomNote.imagePath
        } else if (beforeUpdateRoomNoteBytes == null && updateNoteBytes == null) {
            null // 일단 임시로...
        } else {
            beforeUpdateRoomNote.imagePath?.let { imageStorage.deleteImage(it) }
            imageStorage.saveImage(updateNoteBytes!!)
        }

        Log.i(TAG, "imagePath: $imagePath")

        dao.insertOrUpdateNote(note.toRoomNote(imagePath))
    }

}