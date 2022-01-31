package com.example.catfacts

import com.example.catfacts.model.CatModel
import io.reactivex.Single
import retrofit2.http.GET

interface Api {
    @GET("facts")
    fun getCatFacts(): Single<List<CatModel>>

    @GET("meow")
    fun getRandomImagePath(): Single<String>
}