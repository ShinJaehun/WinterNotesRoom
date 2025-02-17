package com.shinjaehun.winternotesroom.view.notelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.shinjaehun.winternotesroom.R
import com.shinjaehun.winternotesroom.common.makeToast
import com.shinjaehun.winternotesroom.databinding.FragmentNoteListBinding

private const val TAG = "NoteListView"

class NoteListView: Fragment() {

    private lateinit var binding: FragmentNoteListBinding
    private lateinit var viewModel: NoteListViewModel
    private lateinit var adapter: NoteListAdapter
    private lateinit var callback: OnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentNoteListBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.notesRecyclerView.adapter = null
    }

    override fun onStart() {
        super.onStart()

        viewModel = ViewModelProvider(
            this,
            NoteListInjector(requireActivity().application).provideNoteListViewModelFactory()
        ).get(
            NoteListViewModel::class.java
        )

        setupAdapter()
        observeViewModel()

        viewModel.handleEvent(
            NoteListEvent.OnStart
        )

        binding.fabAddNote.setOnClickListener {
            startNoteDetailWithArgs("0")
        }
    }

    private fun setupAdapter() {
        adapter = NoteListAdapter()
        adapter.event.observe(
            viewLifecycleOwner,
            Observer {
                // Log.i(TAG, "what is it? $it")
                // what is it? OnNoteItemClick(position=14)
                viewModel.handleEvent(it)
            }
        )
        binding.notesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.notesRecyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.error.observe(
            viewLifecycleOwner,
            Observer { errorMessage ->
                showErrorState(errorMessage)
            }
        )

        viewModel.noteList.observe(
            viewLifecycleOwner,
            Observer { noteList ->
                adapter.submitList(noteList)
            }
        )

        viewModel.editNote.observe(
            viewLifecycleOwner,
            Observer { noteId ->
                startNoteDetailWithArgs(noteId)
            }
        )

//        viewModel.searchNoteList.observe(
//            viewLifecycleOwner,
//            Observer { noteList ->
//                adapter.submitList(noteList)
//            }
//        )
    }

    private fun startNoteDetailWithArgs(noteId: String) {
        val bundle = Bundle()
        bundle.putString("noteId", noteId)
        findNavController().navigate(R.id.noteDetailView, bundle)
    }

    private fun showErrorState(errorMessage: String?) = makeToast(errorMessage!!)
}