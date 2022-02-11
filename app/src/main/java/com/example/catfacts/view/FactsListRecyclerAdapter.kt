package com.example.catfacts.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.R
import com.example.catfacts.model.CatModel
import com.example.catfacts.view.CatDetailActivity.Companion.catDetailTag


internal class FactsListRecyclerAdapter(private var itemsList: List<CatModel>) :
    RecyclerView.Adapter<FactsListRecyclerAdapter.ItemViewHolder>() {
    internal inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemTextView: TextView = view.findViewById(R.id.itemTextView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemsList[position]
        holder.itemTextView.text = item.text
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, CatDetailActivity::class.java)
            intent.putExtra(catDetailTag, item)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}