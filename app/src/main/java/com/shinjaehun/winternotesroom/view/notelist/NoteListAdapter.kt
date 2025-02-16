package com.shinjaehun.winternotesroom.view.notelist

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.shinjaehun.winternotesroom.common.ColorBLACK
import com.shinjaehun.winternotesroom.common.simpleDate
import com.shinjaehun.winternotesroom.databinding.ItemContainerNoteBinding
import com.shinjaehun.winternotesroom.model.NoteEntity

class NoteListAdapter(
    val event: MutableLiveData<NoteListEvent> = MutableLiveData()
): ListAdapter<NoteEntity, NoteListAdapter.NoteViewHolder>(NoteDiffUtilCallback()) {

    inner class NoteViewHolder(val binding: ItemContainerNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
            ItemContainerNoteBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        getItem(position).let { note ->
            with(holder) {
                binding.tvTitle.text = note.title
                binding.tvDateTime.text = simpleDate(note.dateTime)

                if (note.imagePath.isNullOrEmpty()) {
                    binding.rivImagePreview.visibility = View.GONE
                } else {
                    binding.rivImagePreview.load(note.imagePath)
                    binding.rivImagePreview.visibility = View.VISIBLE
                }

                val gradientDrawable = binding.layoutNote.background as GradientDrawable
                if (note.color.isNullOrEmpty()) {
                    gradientDrawable.setColor(Color.parseColor(ColorBLACK))
                } else {
                    gradientDrawable.setColor(Color.parseColor(note.color))
                }

                binding.layoutNote.setOnClickListener {
                    event.value = NoteListEvent.OnNoteItemClick(position)
                }
            }
        }
    }

}