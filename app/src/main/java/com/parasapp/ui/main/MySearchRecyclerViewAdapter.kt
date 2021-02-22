package com.parasapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.parasapp.R

class MySearchRecyclerViewAdapter(
    private val values: ArrayList<String>, val adapterOnClick: (Any) -> Unit
) : RecyclerView.Adapter<MySearchRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_search, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.textViewItem.text = item
        holder.itemView.setOnClickListener {
            adapterOnClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewItem: TextView = view.findViewById(R.id.item_title)
        override fun toString(): String {
            return super.toString() + " '" + textViewItem.text + "'"
        }
    }
}