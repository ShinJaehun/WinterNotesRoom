package com.shinjaehun.winternotesroom.view.notedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shinjaehun.winternotesroom.databinding.FragmentNoteDetailBinding

class NoteDetailView: Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNoteDetailBinding.inflate(inflater)
        return binding.root
    }
}