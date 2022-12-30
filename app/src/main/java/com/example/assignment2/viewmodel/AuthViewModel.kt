package com.example.assignment2.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment2.model.User
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

    fun setUserEmail(email: String): LiveData<Boolean> =
        repository.setUserEmail(email)

    fun getUserEmail(): String? =
        repository.getUserEmail()

    fun updateUser(email: String, user: User): LiveData<Boolean> =
        repository.updateUser(email,user)

//    fun findUser() =
//        repository.findUser()
//
//    fun getUser(): User? =
//        repository.getUser()
//
    fun addUserInfo(email: String, name: String): LiveData<Boolean> =
        repository.addUserInfo(email, name)


    class Provider(private val repository: AuthRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(repository) as T
            }

            throw IllegalArgumentException("Invalid viewmodel")
        }
    }


}