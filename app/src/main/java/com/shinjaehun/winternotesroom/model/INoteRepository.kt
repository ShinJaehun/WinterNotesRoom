package com.shinjaehun.winternotesroom.model

import com.shinjaehun.winternotesroom.common.Result

interface INoteRepository {
    suspend fun getNotes(): Result<Exception, List<Note>>
    suspend fun getNoteById(noteId: Long): Result<Exception, Note>
    suspend fun deleteNote(noteId: Long): Result<Exception, Unit>
    suspend fun insertOrUpdateNote(note: Note): Result<Exception, Unit>
//    suspend fun searchNote(keyword: String): Result<Exception, List<Note>>
}