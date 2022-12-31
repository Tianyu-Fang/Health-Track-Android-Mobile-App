package com.example.assignment2.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment2.model.Checkin
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class CheckinRepository {

    var db = Firebase.firestore

    fun getAllCheckin(): LiveData<List<Checkin>> {
        val allRecords: MutableLiveData<List<Checkin>> = MutableLiveData()

        val records = ArrayList<Checkin>()

        val TAG = "Checkin"
        db.collection("Checkin")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val note =
                        Checkin(
//                            document.id,
                            document.data["userEmail"].toString(),
                            document.data["symptom"].toString(),
                            document.data["stress_level"].toString(),
                            document.data["treatments"].toString(),
                            document.data["health_factors"].toString()
                        )
                    records.add(note)
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                allRecords.value = records
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }

        return allRecords
    }

    fun addCheckin(checkindata: Checkin) {

        // Add a new document with a generated ID
        val docRef = db.collection("Checkin")
        docRef.get().addOnSuccessListener {
                result ->
            val count = result.size()

            val TAG = "Checkin"
            db.collection("Checkin")
                .document(count.toString())
                .set(checkindata)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: $count")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
            Log.d("add", "Number of documents in the users collection: $count")
        }

    }

    fun getNewCheckin(): LiveData<Checkin> {
        val newRecord: MutableLiveData<Checkin> = MutableLiveData()

        val TAG = "Checkin"
        val docRef = db.collection("Checkin")
        docRef.get().addOnSuccessListener { result ->
            val count = result.size() - 1
            db.collection("Checkin")
                .document(count.toString())
                .get()
                .addOnSuccessListener { result ->
                        val record =
                            Checkin(
                                result.data!!["userEmail"].toString(),
                                result.data!!["symptom"].toString(),
                                result.data!!["stress_level"].toString(),
                                result.data!!["treatments"].toString(),
                                result.data!!["health_factors"].toString()
                            )
                        Log.d(TAG, "${result.id} => ${result.data}")
                    newRecord.value = record
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "Error getting documents: ", exception)
                }

        }

        return newRecord
    }



}