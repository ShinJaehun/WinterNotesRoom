package com.shinjaehun.winternotesroom.view.notelist

import androidx.recyclerview.widget.DiffUtil
import com.shinjaehun.winternotesroom.model.Note

class NoteDiffUtilCallback: DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.noteId == newItem.noteId
    }
}