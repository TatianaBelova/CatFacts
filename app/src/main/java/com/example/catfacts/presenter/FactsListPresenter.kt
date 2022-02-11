package com.example.catfacts.presenter

import com.example.catfacts.model.CatModel
import com.example.catfacts.network.RetrofitHelper
import io.reactivex.Single

const val baseCatsUrl = "https://cat-fact.herokuapp.com/"

class FactsListPresenter {
    private lateinit var retrofitInstance: RetrofitHelper

    fun init() {
       retrofitInstance = RetrofitHelper().apply { initRetrofit(baseCatsUrl) }
    }

    fun callApiRequest(): Single<List<CatModel>> {
        return retrofitInstance.api.getCatFacts()
    }
}