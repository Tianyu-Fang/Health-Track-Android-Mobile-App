package com.example.assignment2.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
//import com.example.firebasefirestore.data.model.Course
import com.example.assignment2.repository.AuthRepository

class AuthViewModel(val repository: AuthRepository) : ViewModel() {

    fun forgotPassword(email: String): LiveData<Boolean> =
        repository.forgotPassword(email)


    fun login(email: String, password: String): LiveData<Boolean> =
        repository.signIn(email, password)


    fun signUp(email: String, password: String): LiveData<Boolean> =
        repository.signUp(email, password)


    fun updatePassword(password: String): LiveData<Boolean> =
        repository.updatePassword(password)

    fun logout() = repository.logout()

    fun isLoggedIn(): LiveData<Boolean> =
        repository.isLoggedIn()



    class Provider(private val repository: AuthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(repository) as T
            }

            throw IllegalArgumentException("Invalid viewmodel")
        }
    }


}