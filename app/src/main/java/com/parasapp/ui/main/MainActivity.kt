package com.parasapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.parasapp.R
import com.parasapp.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AddDataViewModel

    val list = arrayListOf<String>()
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutYear, MyYearFragment.newInstance(1), "my_year_fragment")
            .commit()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayoutStore,
                StoreFragment.newInstance("stores"),
                "my_store_fragment"
            ).commit()
        showSalesOfMonth(Calendar.getInstance())
        val c: Calendar = Calendar.getInstance()
        for (i in Calendar.JANUARY..Calendar.DECEMBER) {
            c.set(Calendar.MONTH, i)
            val c2: String? = c.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US)
            if (c2 != null) {
                list.add(c2)
            }
        }
        cardViewSelectedMonth.setOnClickListener {
            val fm: FragmentManager = supportFragmentManager
            val dialogSearchFragment = MySearchFragment.newInstance(this, list)
            dialogSearchFragment.show(fm, "my_search_fragment")
        }
//        searchView.setOnClickListener {
//            val fm: FragmentManager = supportFragmentManager
//            val dialogSearchFragment = MySearchFragment.newInstance(this, list)
//            dialogSearchFragment.show(fm, "my_search_fragment")
//        }


//        Log.d(TAG, "onCreate: ${getDateOfCollection(Calendar.getInstance().time)}")
//        freeburgDocumentReference = firestoreDb.document("stores/my_store_freeburg/sales/20022021")
//        freeburgDocumentReference.get().addOnSuccessListener {
//            Log.d(TAG, "onCreate: ${it.get("adjustment")}")
//
//        }
//
//        salesCollectionReference =
//            MyApp.firestoreDb.collection("stores/my_store_freeburg/sales_year/year_2021/sales")
//        salesCollectionReference.get().addOnSuccessListener {
//            it.forEach { value ->
//                Log.d(TAG, "onCreate: ${value.get("gross_sales")}")
//                Log.d(TAG, "onCreate: ${value.get("net_sales")}")
//                Log.d(TAG, "onCreate: ${value.get("id")}")
//            }
//        }
//        initViewModelAndObserver()
//        mainViewModel.getCollectionReference("stores/my_store_freeburg/sales")
        floatingActionButtonAddData.setOnClickListener {
            val fragment: AddDataFragment = AddDataFragment.newInstance()
            supportFragmentManager.beginTransaction().replace(R.id.fragmentAddData, fragment)
                .commit()
        }


    }

    private fun showSalesOfMonth(calendar: Calendar) {
        textViewSelectedMonth.text = calendar.getDisplayName(
            Calendar.MONTH,
            Calendar.LONG,
            Locale.US
        )
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE))
        val minimumDateRange = Constants.getDateOfCollection(calendar.time)
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE))
        val maximumDateRange = Constants.getDateOfCollection(calendar.time)
        supportFragmentManager.findFragmentByTag("my_sales_fragment")?.onDestroy()
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayoutSales,
                SalesFragment.newInstance(
                    "stores/my_store_freeburg/sales_year/year_2021/sales",
                    minimumDateRange, maximumDateRange
                ),
                "my_sales_fragment"
            )
            .disallowAddToBackStack()
            .commit()

    }

    fun onClickMonthSelection(month: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, month)
//        searchView.setQuery(calendar.getDisplayName(MONTH, LONG, Locale.US), true)
//        searchView.onActionViewCollapsed();
        showSalesOfMonth(calendar)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.main_menu, menu)
//        val myActionMenuItem: MenuItem = menu.findItem(R.id.action_search)
//        searchView = myActionMenuItem.getActionView() as SearchView
//        searchView.setOnSearchClickListener {
//            val fm: FragmentManager = supportFragmentManager
//            val dialogSearchFragment = MySearchFragment.newInstance(this, list)
//            dialogSearchFragment.show(fm, "my_search_fragment")
//        }
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return true
//            }
//        })
//        return true
//    }
}