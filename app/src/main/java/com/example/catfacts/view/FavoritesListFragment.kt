package com.example.catfacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.MainActivity
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.presenter.FavoritesListPresenter

class FavoritesListFragment : Fragment() {
    private lateinit var recyclerAdapter: FactsListRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainActivity: MainActivity
    private var presenter = FavoritesListPresenter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.favorites_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity
        recyclerView = requireActivity().findViewById(R.id.recyclerView)
        getFavorites()
    }

    private fun getFavorites() {
        displayFavoritesList(presenter.getFavoritesFromDB(mainActivity.db))
    }

    private fun displayFavoritesList(list: List<CatModel>) {
        recyclerAdapter = FactsListRecyclerAdapter(list)
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = recyclerAdapter
        }
    }
}