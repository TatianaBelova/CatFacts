package com.example.catfacts.presenter

import com.example.catfacts.network.Api
import com.example.catfacts.model.CatModel
import com.example.catfacts.network.RetrofitHelper
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

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