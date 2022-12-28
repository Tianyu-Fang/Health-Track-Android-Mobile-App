package com.example.assignment2.repository


import android.util.Log
import androidx.lifecycle.MutableLiveData
//import com.example.firebasefirestore.data.model.Course
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class AuthRepository {
    /* Authentication Functions */
    private lateinit var auth: FirebaseAuth

    fun signIn(email: String, password: String): MutableLiveData<Boolean> {
        val status: MutableLiveData<Boolean> = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener( // This interface is used as a method of being notified when an operation has been acknowledged by the Database servers and can be considered complete
                object : OnCompleteListener<AuthResult?> {
                    override fun onComplete(task: Task<AuthResult?>) {
                        status.value = task.isSuccessful
                    }
                })

        return status
    }

    fun signUp(email: String, password: String): MutableLiveData<Boolean> {
        val status: MutableLiveData<Boolean> = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                status.value = task.isSuccessful
            }
        return status
    }

    fun forgotPassword(email: String): MutableLiveData<Boolean> {
        val status: MutableLiveData<Boolean> = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task -> // This interface is used as a method of being notified when an operation has been acknowledged by the Database servers and can be considered complete
                status.value = task.isSuccessful
            }

        return status
    }

    fun updatePassword(password: String): MutableLiveData<Boolean> {
        val status: MutableLiveData<Boolean> = MutableLiveData()
        auth = FirebaseAuth.getInstance()

        auth.currentUser?.updatePassword(password)?.addOnCompleteListener { task ->
            status.value = task.isSuccessful
        }

        return status
    }

    fun logout() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
    }

    fun isLoggedIn(): MutableLiveData<Boolean> {
        val status: MutableLiveData<Boolean> = MutableLiveData()

        auth = FirebaseAuth.getInstance()

        status.value = auth.currentUser != null

        return status
    }


    /* Database functions */
    // Realtime DB
//    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
//    lateinit var databaseReference: DatabaseReference

    // Firestore
    var db = Firebase.firestore


    companion object {
        val repository = AuthRepository()
    }

}