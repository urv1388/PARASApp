package com.parasapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import com.parasapp.R
import com.parasapp.core.MyApp
import com.parasapp.model.MyYear

/**
 * A fragment representing a list of Items.
 */
class MyYearFragment : Fragment() {

    private val TAG = "MyYearFragment"
    lateinit var myYearRecyclerViewAdapter: MyYearRecyclerViewAdapter;

    lateinit var myYearCollectionReference: CollectionReference
    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onStart() {
        super.onStart()
        myYearRecyclerViewAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myYearRecyclerViewAdapter.stopListening()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_year_list, container, false)
        myYearCollectionReference =
            MyApp.firestoreDb.collection("/stores/my_store_freeburg/sales_year")
        val query = myYearCollectionReference.orderBy("year", Query.Direction.DESCENDING)
        val fireStoreRecyclerOptions =
            FirestoreRecyclerOptions.Builder<MyYear>().setQuery(query, MyYear::class.java).build()
        myYearRecyclerViewAdapter = MyYearRecyclerViewAdapter(fireStoreRecyclerOptions)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = myYearRecyclerViewAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MyYearFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}