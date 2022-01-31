package com.example.catfacts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.presenter.FactsListPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FactsListFragment : Fragment() {
    private var presenter: FactsListPresenter = FactsListPresenter().apply { init() }
    private val compositeDisposable = CompositeDisposable()

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
        val output: StringBuilder = StringBuilder()
        for (cat: CatModel in list) {
            output.append(cat.text).append("\n")
        }
        val textView: TextView = requireView().findViewById(R.id.catsTextView)
        textView.text = output.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}