package com.parasapp.core

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

class MyApp : Application() {
    companion object {
        lateinit var firestoreDb: FirebaseFirestore
    }

    override fun onCreate() {
        super.onCreate()
        firestoreDb = FirebaseFirestore.getInstance()
    }
}