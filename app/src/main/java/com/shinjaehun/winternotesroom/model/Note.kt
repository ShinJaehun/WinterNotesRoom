package com.shinjaehun.winternotesroom.model

data class Note(
    val noteId: String,
    val title: String,
    val contents: String?,
    val dateTime: String,
//    val imagePath: String?,
    val imageBytes: ByteArray?,
    val color: String?,
    val webLink: String?,
)