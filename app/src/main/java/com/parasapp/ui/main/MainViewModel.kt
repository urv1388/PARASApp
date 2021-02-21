package com.parasapp.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.parasapp.R
import com.parasapp.core.MyApp
import com.parasapp.utils.Resource
import com.parasapp.utils.Utils
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application, val firestore: FirebaseFirestore) :
    AndroidViewModel(application) {

    private val TAG = "MainViewModel"

    private val _collectionReferenceResponse =
        MutableLiveData<Resource<QuerySnapshot?>>()
    val collectionReferenceResponse: LiveData<Resource<QuerySnapshot?>> =
        _collectionReferenceResponse

    fun getCollectionReference(path: String) {
        viewModelScope.launch {
            _collectionReferenceResponse.postValue(Resource.Loading())
            delay(1500)
            try {
                if (Utils.hasInternetConnection(getApplication())) {
                    firestore.collection(path).get().addOnSuccessListener {
                        Log.d(TAG, "getCollectionReference: ")
                        _collectionReferenceResponse.postValue(Resource.Success(it))
                    }.addOnFailureListener {
                        _collectionReferenceResponse.postValue(
                            Resource.Error(it.message ?: it.printStackTrace().toString())
                        )
                    }
                } else {
                    _collectionReferenceResponse.postValue(
                        Resource.Error(
                            getApplication<MyApp>().getString(
                                R.string.no_internet_connection
                            )
                        )
                    )
                }
            } catch (t: Throwable) {
                when (t) {
                    is IOException -> {
                        _collectionReferenceResponse.postValue(
                            Resource.Error(
                                getApplication<MyApp>().getString(
                                    R.string.network_failure
                                )
                            )
                        )
                    }
                    else -> {
                        _collectionReferenceResponse.postValue(
                            Resource.Error(
                                getApplication<MyApp>().getString(
                                    R.string.conversion_error
                                )
                            )
                        )
                    }
                }
            }

        }
    }

}