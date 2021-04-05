package com.parasapp.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.FirebaseFirestore

class AddDataViewModel(application: Application, val firebaseFirestore: FirebaseFirestore?) :
    AndroidViewModel(application) {

}