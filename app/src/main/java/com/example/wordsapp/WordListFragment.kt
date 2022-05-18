package com.example.wordsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentWordListBinding

//NavHostFragment

private var _binding: FragmentWordListBinding? = null
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView

private lateinit var letterID: String

class WordListFragment : Fragment() {
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterID = it.getString(LETTER).toString()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated (view: View, savedInstanceState: Bundle?) {
        // Retrieve the LETTER from the Intent extras
        // intent.extras.getString returns String? (String or null)
        // so toString() guarantees that the value will be a String
        // not working intent(fragments): val letterId  = activity?.intent?.extras?.getString(LETTER).toString()

        recyclerView = binding.recyclerView
        recyclerView.adapter = WordAdapter(letterID, requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}