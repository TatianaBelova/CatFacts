package com.example.catfacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.presenter.FavoritesListPresenter

class FavoritesListFragment : Fragment() {
    private lateinit var recyclerAdapter: FactsListRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private var presenter = FavoritesListPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.favorites_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = requireActivity().findViewById(R.id.favoritesRecyclerView)
        getFavorites()
    }

    private fun getFavorites() {
        val list: List<CatModel> = presenter.getFavoritesFromDB()
        val emptyTextView = requireActivity().findViewById<TextView>(R.id.emptyTextView)
        if (list.isEmpty()) {
            emptyTextView.visibility = VISIBLE
            recyclerView.visibility = GONE
        } else {
            emptyTextView.visibility = GONE
            recyclerView.visibility = VISIBLE
            displayFavoritesList(list)
        }
    }

    private fun displayFavoritesList(list: List<CatModel>) {
        recyclerAdapter = FactsListRecyclerAdapter(list)
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = recyclerAdapter
        }
    }
}