package com.example.catfacts.presenter

import com.example.catfacts.database.DbHelper
import com.example.catfacts.model.CatModel
import org.kodein.db.DB
import org.kodein.db.find
import org.kodein.db.useModels

class FavoritesListPresenter {
    fun getFavoritesFromDB() : List<CatModel> {
        val db: DB? = DbHelper.getDB()
        if (db != null) {
            return db.find<CatModel>().all().useModels { it.toList() }
        }
        return emptyList()
    }
}