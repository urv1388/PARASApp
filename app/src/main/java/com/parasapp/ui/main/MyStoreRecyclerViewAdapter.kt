package com.parasapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.parasapp.R
import com.parasapp.model.MyStore

class MyStoreRecyclerViewAdapter(
    options: FirestoreRecyclerOptions<MyStore>
) : FirestoreRecyclerAdapter<MyStore, MyStoreRecyclerViewAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_list_item, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.item_title)

        override fun toString(): String {
            return super.toString() + " '" + titleTextView.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MyStore) {
        holder.titleTextView.text = model.store_name
    }
}