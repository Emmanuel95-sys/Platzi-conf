package com.platzi.platziconf2.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.platzi.platziconf2.model.Conference
import com.platzi.platziconf2.model.Speaker

const val  CONFERENCES_COLLECTION_NAME = "conferences"
const val  SPEAKERS_COLLECTION_NAME = "speakers"

class FireStoreService {
//configuracion e instancia inicial
    //conexcion directa a la base datos

    val fireBaseFirestore = FirebaseFirestore.getInstance()

    //configuracion para persistir los datos e implementar modo offline.
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    //inicilizador, se comporta como un construcotr
    // se ejecuta automaticamente cuando instanciamos la clase
    init {
        //los datos persisten
        fireBaseFirestore.firestoreSettings = settings
    }

    fun getSpeakersFromFireStore(callback: Callback<List<Speaker>>){
        fireBaseFirestore.collection(SPEAKERS_COLLECTION_NAME)
            .orderBy("category")
            .get()
            .addOnSuccessListener { speakersFromFireStore ->
                for (speakerDoc in speakersFromFireStore){
                    val speakersPojos = speakersFromFireStore.toObjects(Speaker::class.java)
                    callback.onSuccess(speakersPojos)
                    break
                }
            }
    }

    fun getScheduleConferencesFromFireStore(callback: Callback<List<Conference>>){
        fireBaseFirestore.collection(CONFERENCES_COLLECTION_NAME)
            .get()
            .addOnSuccessListener { conferencesFromFireStore ->
                for (conferenceDoc in conferencesFromFireStore){
                    val conferencesPojos = conferencesFromFireStore.toObjects(Conference::class.java)
                    callback.onSuccess(conferencesPojos)
                    break
                }
            }
    }


}