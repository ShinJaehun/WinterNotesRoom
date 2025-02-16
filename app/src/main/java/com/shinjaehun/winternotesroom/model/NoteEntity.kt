package com.shinjaehun.winternotesroom.model

data class NoteEntity(
    val noteId: String?,
    val title: String,
    val contents: String?,
    val dateTime: String,
    val imagePath: String?,
    val color: String?,
    val webLink: String?,
)