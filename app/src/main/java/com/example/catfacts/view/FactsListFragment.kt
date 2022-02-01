package com.example.catfacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.presenter.FactsListPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FactsListFragment : Fragment() {
    private var presenter: FactsListPresenter = FactsListPresenter().apply { init() }
    private val compositeDisposable = CompositeDisposable()
    private lateinit var recyclerAdapter: FactsListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.facts_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCatFactsFromApi()
    }

    private fun getCatFactsFromApi() {
        compositeDisposable.add(
            presenter.callApiRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayCatList)
        )
    }

    private fun displayCatList(list: List<CatModel>) {
        val recyclerView: RecyclerView = requireActivity().findViewById(R.id.recyclerView)
        recyclerAdapter = FactsListRecyclerAdapter(list)
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = recyclerAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}