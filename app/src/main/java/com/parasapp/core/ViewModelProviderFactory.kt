package com.parasapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import com.parasapp.ui.main.AddDataViewModel
import com.parasapp.ui.main.MainViewModel

class ViewModelProviderFactory(
    val app: Application, val firestore: FirebaseFirestore?
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(app, firestore) as T
        } else if (modelClass.isAssignableFrom(AddDataViewModel::class.java)) {
            return AddDataViewModel(app,firestore) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}