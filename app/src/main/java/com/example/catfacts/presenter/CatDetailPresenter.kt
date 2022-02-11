package com.example.catfacts.presenter

import com.example.catfacts.model.ImageModel
import com.example.catfacts.network.RetrofitHelper
import retrofit2.Call

const val baseCatImageUrl = "https://aws.random.cat/"

class CatDetailPresenter {
    private lateinit var retrofitInstance: RetrofitHelper

    fun init() {
        retrofitInstance = RetrofitHelper().apply { initRetrofit(baseCatImageUrl) }
    }

    fun callApiRequest() : Call<ImageModel> {
        return retrofitInstance.api.getRandomImagePath()
    }
}