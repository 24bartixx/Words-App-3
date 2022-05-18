package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wordsapp.databinding.FragmentLetterListBinding

//NavHostFragment

// _ means that variable will not be called directly
private var _binding: FragmentLetterListBinding? = null
// get() - value cannot be assigned anywhere else
private val binding get() = _binding!!
private lateinit var recyclerView: RecyclerView
private var isLinearLayoutManager = true

class LetterListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        changeLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    override fun onOptionsItemSelected (item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                changeLayout()
                setIcon(item)
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
                return false
            }
        }
    }

    private fun changeLayout () {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager (context)
            recyclerView.adapter = LetterAdapter()
        }
        else {
            recyclerView.layoutManager = GridLayoutManager (context, 4)
            recyclerView.adapter = LetterAdapter()
        }
    }

    private fun setIcon(item: MenuItem?) {
        if (item == null) return

        if (isLinearLayoutManager) item.setIcon(R.drawable.ic_grid_layout)
        else item.setIcon(R.drawable.ic_linear_layout)

        //item.icon =
        //    if (isLinearLayoutManager) ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
        //else ContextCompat.getDrawable (this.requireContext(), R.drawable.ic_grid_layout)
    }
}