package com.parasapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.parasapp.R

/**
 * A fragment representing a list of Items.
 */
class MySearchFragment(val activity: MainActivity) : BottomSheetDialogFragment() {
    lateinit var itemList: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            itemList = it.getStringArrayList(ARG_ITEM_LIST) as ArrayList<String>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = MySearchRecyclerViewAdapter(itemList) { mItem ->
                    activity.onClickMonthSelection(mItem as Int)
                    dialog!!.dismiss()
                }
            }
        }
        dialog!!.setCancelable(false)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_ITEM_LIST = "item-list"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(activity: MainActivity, itemList: ArrayList<String>) =
            MySearchFragment(activity = activity).apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_ITEM_LIST, itemList)
                }
            }
    }


}