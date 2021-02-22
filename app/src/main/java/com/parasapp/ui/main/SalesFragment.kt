package com.parasapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.CollectionReference
import com.parasapp.R
import com.parasapp.core.MyApp
import com.parasapp.model.MySales

/**
 * A fragment representing a list of Items.
 */
class SalesFragment : Fragment() {

    private var salesCollectionPath: String? = null
    private var salesSelectionStartDates: String? = null
    private var salesSelectionEndDates: String? = null
    lateinit var mySalesCollectionReference: CollectionReference
    lateinit var mySalesRecyclerViewAdapter: MySalesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            salesCollectionPath = it.getString(ARG_SALES_COLLECTION)
            salesSelectionStartDates = it.getString(ARG_SALES_SELECTION_START_DATE)
            salesSelectionEndDates = it.getString(ARG_SALES_SELECTION_END_DATE)
        }
    }

    override fun onStart() {
        super.onStart()
        mySalesRecyclerViewAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        mySalesRecyclerViewAdapter.stopListening()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sales_list, container, false)
        mySalesCollectionReference = MyApp.firestoreDb.collection(
            salesCollectionPath ?: "stores/my_store_freeburg/sales_year/year_2021/sales"
        )
        val query = if (salesSelectionStartDates != null && salesSelectionEndDates != null) {
            mySalesCollectionReference.whereGreaterThanOrEqualTo(
                "id",
                salesSelectionStartDates!!
            ).whereLessThanOrEqualTo("id", salesSelectionEndDates!!)
        } else {
            mySalesCollectionReference.limit(50)
        }
        val fireStoreRecyclerOptions =
            FirestoreRecyclerOptions.Builder<MySales>().setQuery(query, MySales::class.java).build()
        mySalesRecyclerViewAdapter = MySalesRecyclerViewAdapter(fireStoreRecyclerOptions)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = mySalesRecyclerViewAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_SALES_COLLECTION = "sales_path"
        const val ARG_SALES_SELECTION_START_DATE = "sales_selection_start_date"
        const val ARG_SALES_SELECTION_END_DATE = "sales_selection_end_date"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(
            pathCollection: String? = null,
            startDate: String? = null,
            endDate: String? = null
        ) =
            SalesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SALES_COLLECTION, pathCollection)
                    putString(ARG_SALES_SELECTION_START_DATE, startDate)
                    putString(ARG_SALES_SELECTION_END_DATE, endDate)
                }
            }
    }
}