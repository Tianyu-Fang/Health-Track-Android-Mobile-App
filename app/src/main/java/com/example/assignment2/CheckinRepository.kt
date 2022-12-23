package com.example.assignment2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment2.model.Checkin
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CheckinRepository {

    var db = Firebase.firestore

//    fun getAllNotes(): LiveData<List<Checkin>> {
//        val allNotes: MutableLiveData<List<Checkin>> = MutableLiveData()
//
//        val notes = ArrayList<Checkin>()
//
//        val TAG = "NOTE"
//        db.collection("Checkin")
//            .get()
//            .addOnSuccessListener { result ->
//                for (document in result) {
//                    val note =
//                        Checkin(
////                            document.id,
//                            document.data["word"].toString()
//                        )
//                    notes.add(note)
//                    Log.d(TAG, "${document.id} => ${document.data}")
//                }
//                allNotes.value = notes
//            }
//            .addOnFailureListener { exception ->
//                Log.d(TAG, "Error getting documents: ", exception)
//            }
//
//        return allNotes
//    }

    fun addNote(checkindata: Checkin) {

        // Add a new document with a generated ID
        val TAG = "Note"
        db.collection("Checkin")
            .add(checkindata)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}