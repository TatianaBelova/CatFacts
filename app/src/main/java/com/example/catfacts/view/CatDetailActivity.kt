package com.example.catfacts.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.catfacts.MainActivity
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.model.ImageModel
import com.example.catfacts.presenter.CatDetailPresenter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CatDetailActivity : AppCompatActivity() {
    private lateinit var mainActivity: MainActivity
    private var presenter = CatDetailPresenter().apply { init() }

    companion object {
        const val catDetailTag = "catDetailTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cat_detail)
        title = "Факт"
        mainActivity = getActivity as MainActivity
        initializeView()
    }

    private fun initializeView() {
        val catFact : CatModel = intent?.extras?.getSerializable(catDetailTag) as CatModel
        this.findViewById<TextView>(R.id.catDetailTextView).text = catFact.text

        this.findViewById<Button>(R.id.addToFavorite).setOnClickListener {
            db.put(catFact)
        }

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