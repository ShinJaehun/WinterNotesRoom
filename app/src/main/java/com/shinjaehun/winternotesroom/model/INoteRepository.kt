package com.shinjaehun.winternotesroom.model

import com.shinjaehun.winternotesroom.common.Result

interface INoteRepository {
    suspend fun getNotes(): Result<Exception, List<NoteEntity>>
    suspend fun getNoteById(noteId: String): Result<Exception, NoteEntity>
    suspend fun deleteNote(noteEntity: NoteEntity): Result<Exception, Unit>
    suspend fun insertOrUpdateNote(noteEntity: NoteEntity): Result<Exception, Unit>
//    suspend fun searchNote(keyword: String): Result<Exception, List<Note>>
}