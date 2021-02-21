package com.parasapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.parasapp.R
import java.util.*


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutYear, MyYearFragment.newInstance(1), "my_year_fragment")
            .addToBackStack(null)
            .commit()

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutSales, SalesFragment.newInstance(), "my_sales_fragment")
            .addToBackStack(null)
            .commit()


        val c: Calendar = Calendar.getInstance()

        Log.d(TAG, "onCreate: ${c.getActualMinimum(Calendar.MONTH)}")
        Log.d(TAG, "onCreate: ${c.getActualMaximum(Calendar.MONTH)}")


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


    }

//    private fun initViewModelAndObserver() {
//        val factory = ViewModelProviderFactory(application, firestoreDb)
//        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
//        mainViewModel.collectionReferenceResponse.observe(this, {
//            Log.d(TAG, "initViewModelAndObserver: ${it.data?.size()}")
//        })
//    }
}