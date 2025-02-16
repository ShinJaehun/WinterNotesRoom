package com.shinjaehun.winternotesroom.view.notelist

import androidx.recyclerview.widget.DiffUtil
import com.shinjaehun.winternotesroom.model.NoteEntity

class NoteDiffUtilCallback: DiffUtil.ItemCallback<NoteEntity>() {
    override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
        return oldItem.noteId == newItem.noteId
    }
}