package com.parasapp.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestoreException
import com.parasapp.R
import com.parasapp.model.MyYear

class MyYearRecyclerViewAdapter(
    options: FirestoreRecyclerOptions<MyYear>
) : FirestoreRecyclerAdapter<MyYear, MyYearRecyclerViewAdapter.ViewHolder>(options) {

    private val TAG = "MyYearRecyclerViewAdapt"

    override fun onError(e: FirebaseFirestoreException) {
        Log.d(TAG, "onError: ${e.message}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_year, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val viewTitle: TextView = view.findViewById(R.id.item_title)
        override fun toString(): String {
            return super.toString() + " '" + viewTitle.text + "'"
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: MyYear) {
        Log.d(TAG, "onBindViewHolder: ${model.year}")
        holder.viewTitle.text = model.year
    }
}