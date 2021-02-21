package com.parasapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.parasapp.R
import com.parasapp.model.MySales

class MySalesRecyclerViewAdapter(
    options: FirestoreRecyclerOptions<MySales>
) : FirestoreRecyclerAdapter<MySales, MySalesRecyclerViewAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sales, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val grossTextView: TextView = view.findViewById(R.id.grossTextView)
        val nettTextView: TextView = view.findViewById(R.id.nettTextView)
        override fun toString(): String {
            return super.toString() + " '" + grossTextView.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MySales) {
        holder.grossTextView.text = model.gross.toString()
        holder.nettTextView.text = model.nett.toString()
    }
}