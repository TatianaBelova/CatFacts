package com.example.catfacts.network

import com.example.catfacts.model.CatModel
import com.example.catfacts.model.ImageModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("facts")
    fun getCatFacts(): Single<List<CatModel>>

    @GET("meow")
    fun getRandomImagePath(): Call<ImageModel>
}