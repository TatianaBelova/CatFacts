package com.example.catfacts.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.catfacts.database.DbHelper
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.model.ImageModel
import com.example.catfacts.presenter.CatDetailPresenter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class ButtonType { ADD, DELETE }

class CatDetailActivity : AppCompatActivity() {
    private var presenter = CatDetailPresenter().apply { init() }
    lateinit var catFact: CatModel
    lateinit var addToFavoriteButton: Button
    lateinit var buttonType: ButtonType

    companion object {
        const val catDetailTag = "catDetailTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_detail)
        initializeView()
    }

    private fun initializeView() {
        title = "Факт"
        catFact = intent?.extras?.getSerializable(catDetailTag) as CatModel
        this.findViewById<TextView>(R.id.catDetailTextView).text = catFact.text
        addToFavoriteButton = this.findViewById(R.id.addToFavorite)
        setUpImage()
        setUpButtonClickListener()
    }

    private fun setUpImage() {
        presenter.callApiRequest().enqueue(
            object : Callback<ImageModel> {
                override fun onResponse(call: Call<ImageModel>, response: Response<ImageModel>) {
                    if (response.isSuccessful) {
                        val imageView: ImageView = findViewById(R.id.imageView)
                        Picasso.with(applicationContext)
                            .load(response.body()?.file)
                            .fit().centerCrop()
                            .placeholder(R.drawable.loader)
                            .into(imageView)
                    }
                }

                override fun onFailure(call: Call<ImageModel>, t: Throwable) {
                    Log.e("onFailure", "Error is : $t")
                }
            }
        )
    }

    private fun setUpButtonClickListener() {
        getButtonType()
        addToFavoriteButton.setOnClickListener {
            when (buttonType) {
                ButtonType.ADD -> {
                    DbHelper.insertIntoDB(catFact)
                }
                ButtonType.DELETE -> {
                    DbHelper.deleteFromDB(catFact.uid)
                }
            }
            getButtonType()
        }
    }

    private fun getButtonType() {
        if (DbHelper.getFromDBByIndex(catFact.uid) != null) {
            buttonType = ButtonType.DELETE
            addToFavoriteButton.text = "Удалить из избранного"
        } else {
            buttonType = ButtonType.ADD
            addToFavoriteButton.text = "Добавить в избранное"
        }
    }
}