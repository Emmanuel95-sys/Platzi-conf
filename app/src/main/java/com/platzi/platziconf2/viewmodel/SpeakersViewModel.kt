package com.platzi.platziconf2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.platziconf2.model.Speaker
import com.platzi.platziconf2.network.Callback
import com.platzi.platziconf2.network.FireStoreService
import java.lang.Exception

class SpeakersViewModel : ViewModel(){

    val fireStoreService = FireStoreService()
    var listSpeakers : MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun getSpeakersFromFireStoreService(){
        fireStoreService.getSpeakersFromFireStore(object : Callback<List<Speaker>>{
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun refreshSpeakers(){
        getSpeakersFromFireStoreService()
    }

    fun processFinished(){
        isLoading.value = true
    }

}