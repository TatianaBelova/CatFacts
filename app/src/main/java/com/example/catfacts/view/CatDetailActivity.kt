package com.example.catfacts.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.PackageManagerCompat.LOG_TAG
import com.example.catfacts.R
import com.example.catfacts.model.ImageModel
import com.example.catfacts.presenter.CatDetailPresenter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatDetailActivity : AppCompatActivity() {
    private var presenter = CatDetailPresenter().apply { init() }

    companion object {
        const val catDetailTag = "catDetailTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_detail)
        title = "Факт"
        initializeView()
    }

    private fun initializeView() {
        this.findViewById<TextView>(R.id.catDetailTextView).text =
            intent?.extras?.getString(catDetailTag)

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
}