package com.example.catfacts.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorPlaceholder : View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.facts_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCatFactsFromApi()
        recyclerView = requireActivity().findViewById(R.id.recyclerView)
        errorPlaceholder = requireActivity().findViewById(R.id.errorView)
        errorPlaceholder.findViewById<Button>(R.id.tryAgain).setOnClickListener {
            getCatFactsFromApi()
        }
    }

    private fun getCatFactsFromApi() {
        compositeDisposable.add(
            presenter.callApiRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::displayCatList, this::doOnError)
        )
    }

    private fun displayCatList(list: List<CatModel>) {
        recyclerView.visibility = VISIBLE
        errorPlaceholder.visibility = GONE
        recyclerAdapter = FactsListRecyclerAdapter(list)
        recyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = recyclerAdapter
        }
    }

    private fun doOnError(error: Throwable) {
        Log.e(null, error.message.toString())
        recyclerView.visibility = GONE
        errorPlaceholder.visibility = VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}