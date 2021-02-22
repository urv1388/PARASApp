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
import com.parasapp.model.MyStore

/**
 * A fragment representing a list of Items.
 */
class StoreFragment : Fragment() {

    private var storesCollectionPath: String? = ""
    lateinit var myStoreCollectionReference: CollectionReference
    lateinit var myStoreRecyclerViewAdapter: MyStoreRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            storesCollectionPath = it.getString(ARG_STORE_COLLECTION)
        }
    }

    override fun onStart() {
        super.onStart()
        myStoreRecyclerViewAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        myStoreRecyclerViewAdapter.stopListening()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_store_list, container, false)
        myStoreCollectionReference = MyApp.firestoreDb.collection(
            storesCollectionPath ?: "stores"
        )
        val query = myStoreCollectionReference.limit(50)
        val fireStoreRecyclerOptions =
            FirestoreRecyclerOptions.Builder<MyStore>().setQuery(query, MyStore::class.java).build()
        myStoreRecyclerViewAdapter = MyStoreRecyclerViewAdapter(fireStoreRecyclerOptions)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = myStoreRecyclerViewAdapter
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_STORE_COLLECTION = "store_path"


        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(storePath: String? = null) =
            StoreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_STORE_COLLECTION, storePath)
                }
            }
    }
}