package com.example.catfacts.model

import org.kodein.db.model.Id
import java.io.Serializable

data class CatModel(
    @Id val uid: String,
    val id: String,
    val text: String,
) : Serializable