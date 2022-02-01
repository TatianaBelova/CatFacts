package com.example.catfacts.view

import com.example.catfacts.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.catfacts.model.CatModel

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
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}