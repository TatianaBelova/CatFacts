package com.example.catfacts.presenter

import com.example.catfacts.model.CatModel
import org.kodein.db.DB
import org.kodein.db.find
import org.kodein.db.useModels

class FavoritesListPresenter {
    fun getFavoritesFromDB(db: DB) : List<CatModel> {
        return db.find<CatModel>().all().useModels { it.toList() }
    }
}