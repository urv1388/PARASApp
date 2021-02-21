package com.parasapp.core

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore

class MyApp : Application() {
    lateinit var firestore: FirebaseFirestore

    override fun onCreate() {
        super.onCreate()
        firestore = FirebaseFirestore.getInstance()
    }
}