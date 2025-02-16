package com.shinjaehun.winternotesroom.model

import com.shinjaehun.winternotesroom.common.Result
import com.shinjaehun.winternotesroom.common.toNoteEntity
import com.shinjaehun.winternotesroom.common.toNoteListFromRoomNoteList
import com.shinjaehun.winternotesroom.common.toRoomNote

class NoteRepoImpl(
    private val dao: NoteDao
): INoteRepository {
    override suspend fun getNotes(): Result<Exception, List<NoteEntity>> {
        return getLocalNotes()
    }

    override suspend fun getNoteById(noteId: String): Result<Exception, NoteEntity> {
        return getLocalNote(noteId)
    }

    override suspend fun deleteNote(noteEntity: NoteEntity): Result<Exception, Unit> {
        return deleteLocalNote(noteEntity)
    }

    override suspend fun insertOrUpdateNote(noteEntity: NoteEntity): Result<Exception, Unit> {
        return insertOrUpdateLocalNote(noteEntity)
    }

    private suspend fun getLocalNotes(): Result<Exception, List<NoteEntity>> = Result.build {
        dao.getNotes().toNoteListFromRoomNoteList()
    }

    private suspend fun getLocalNote(noteId: String): Result<Exception, NoteEntity> = Result.build {
        dao.getNoteById(noteId).toNoteEntity
    }

    private suspend fun deleteLocalNote(noteEntity: NoteEntity): Result<Exception, Unit> = Result.build {
        dao.deleteNote(noteEntity.toRoomNote)
    }

    private suspend fun insertOrUpdateLocalNote(noteEntity: NoteEntity): Result<Exception, Unit> = Result.build {
        dao.insertOrUpdateNote(noteEntity.toRoomNote)
    }

}