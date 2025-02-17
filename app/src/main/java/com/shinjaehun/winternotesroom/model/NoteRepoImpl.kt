package com.shinjaehun.winternotesroom.model

import com.shinjaehun.winternotesroom.common.Result
import com.shinjaehun.winternotesroom.common.toNote
import com.shinjaehun.winternotesroom.common.toNoteListFromRoomNoteList
import com.shinjaehun.winternotesroom.common.toRoomNote

class NoteRepoImpl(
    private val dao: NoteDao
): INoteRepository {
    override suspend fun getNotes(): Result<Exception, List<Note>> {
        return getLocalNotes()
    }

    override suspend fun getNoteById(noteId: String): Result<Exception, Note> {
        return getLocalNote(noteId)
    }

    override suspend fun deleteNote(note: Note): Result<Exception, Unit> {
        return deleteLocalNote(note)
    }

    override suspend fun insertOrUpdateNote(note: Note): Result<Exception, Unit> {
        return insertOrUpdateLocalNote(note)
    }

    private suspend fun getLocalNotes(): Result<Exception, List<Note>> = Result.build {
        dao.getNotes().toNoteListFromRoomNoteList()
    }

    private suspend fun getLocalNote(noteId: String): Result<Exception, Note> = Result.build {
        dao.getNoteById(noteId).toNote
    }

    private suspend fun deleteLocalNote(note: Note): Result<Exception, Unit> = Result.build {
        dao.deleteNote(note.toRoomNote)
    }

    private suspend fun insertOrUpdateLocalNote(note: Note): Result<Exception, Unit> = Result.build {
        dao.insertOrUpdateNote(note.toRoomNote)
    }

}