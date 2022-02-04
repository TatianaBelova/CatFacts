package com.example.catfacts.network

import com.example.catfacts.presenter.baseCatsUrl
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    lateinit var api: Api

    fun initRetrofit(baseUrl: String) {
        val retrofit = createRetrofit(baseUrl)
        api = retrofit.create(Api::class.java)
    }

    private fun createRetrofit(baseUrl: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}