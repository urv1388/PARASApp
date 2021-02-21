package com.parasapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.parasapp.R
import com.parasapp.core.ViewModelProviderFactory


class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var firestoreDb: FirebaseFirestore
    lateinit var mainViewModel: MainViewModel
    //    lateinit var storeCollectionReference: CollectionReference
    lateinit var freeburgDocumentReference: DocumentReference

    lateinit var salesCollectionReference: CollectionReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: ")











//        Log.d(TAG, "onCreate: ${getDateOfCollection(Calendar.getInstance().time)}")
//        freeburgDocumentReference = firestoreDb.document("stores/my_store_freeburg/sales/20022021")
//        freeburgDocumentReference.get().addOnSuccessListener {
//            Log.d(TAG, "onCreate: ${it.get("adjustment")}")
//
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