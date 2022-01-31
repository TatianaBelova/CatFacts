package com.example.catfacts.presenter

import com.example.catfacts.Api
import com.example.catfacts.model.CatModel
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val baseCatsUrl = "https://cat-fact.herokuapp.com/"

class FactsListPresenter {
    private lateinit var api: Api

    fun init() {
        val retrofit = createRetrofit()
        api = retrofit.create(Api::class.java)
    }

    fun callApiRequest(): Single<List<CatModel>> {
        return api.getCatFacts()
    }

    private fun createRetrofit(): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseCatsUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}