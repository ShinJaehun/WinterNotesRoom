package com.shinjaehun.winternotesroom.view.notelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.shinjaehun.winternotesroom.databinding.FragmentNoteListBinding

class NoteListView: Fragment() {

    private lateinit var binding: FragmentNoteListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        requireActivity().onBackPressedDispatcher.addCallback(this) {
//            activity?.finish()
//        }

        binding = FragmentNoteListBinding.inflate(inflater)
        return binding.root
    }
}