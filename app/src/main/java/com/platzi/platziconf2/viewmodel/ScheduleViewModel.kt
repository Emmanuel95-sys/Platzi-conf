package com.platzi.platziconf2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.platzi.platziconf2.model.Conference
import com.platzi.platziconf2.network.Callback
import com.platzi.platziconf2.network.FireStoreService
import java.lang.Exception

//se encarga de comunicarse con firestore y UI
//las calses View model DEBEN HEREDAR de viewModel()
class ScheduleViewModel : ViewModel() {
    val fireStoreService = FireStoreService()
    //fusioando viewModel con LiveData
    //Wrapper de los datos que vamos a observar activamente
    var listSchedule: MutableLiveData<List<Conference>> = MutableLiveData()
    //varable booleana que nos permitira actulizar nuestra ui de carga
    var isLoading= MutableLiveData<Boolean>()

    fun getScheduleFromFireStoreService(){
        fireStoreService.getScheduleConferencesFromFireStore(object : Callback<List<Conference>>{
            override fun onSuccess(result: List<Conference>?) {
                listSchedule.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()

            }
        })
    }

    fun refreshSchedule(){
        getScheduleFromFireStoreService()
    }

    fun processFinished(){
        isLoading.value = true
    }

}