package com.example.catfacts.database

import com.example.catfacts.model.CatModel
import org.kodein.db.DB
import org.kodein.db.deleteById
import org.kodein.db.getById
import org.kodein.db.impl.open

class DbHelper {
    companion object {
        private var db: DB? = null

        fun createDB(pathToDB: String) {
            db = DB.open(pathToDB)
        }

        fun getDB(): DB? {
            return db
        }

        fun insertIntoDB(catFact: CatModel) {
            db?.put(catFact)
        }

        fun deleteFromDB(uid: String) {
            val cat = db?.deleteById<CatModel>(uid)
            print(cat)
        }

        fun getFromDBByIndex(uid: String) : CatModel? {
            return db?.getById(uid)
        }
    }
}