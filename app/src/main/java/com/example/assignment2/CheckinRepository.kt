package com.example.assignment2

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.assignment2.model.Checkin
import com.example.assignment2.networks.RetrofitApi
import com.example.assignment2.networks.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckinRepository {

    fun getAllNotes(): LiveData<List<Checkin>> {
        val allNotes: MutableLiveData<List<Checkin>> = MutableLiveData()

        var api: RetrofitApi? = null
        api = RetrofitClient.getInstance()?.getApi()
//        val response: Response<List<Note>> = api?.getAllNotes()!!
//        CoroutineScope(Dispatchers.IO).launch {
//            if(response!!.isSuccessful) {
//                withContext(Dispatchers.Main) {
//                    allNotes.value = response.body()
//                }
//            }
//        }
        val call = api?.getAllNotes()

        call?.enqueue(object : Callback<List<Checkin>> {
            override fun onResponse(call: Call<List<Checkin>>, response: Response<List<Checkin>>) {
                if(response.isSuccessful) {
                    allNotes.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<Checkin>>, t: Throwable) {
                Log.e("ERROR: ", t.message!!)
            }

        })
        return allNotes
    }


    // insert API
    suspend fun addNote(note: Checkin) {
//        noteDao.addNote(note)
        var api: RetrofitApi? = null
        api = RetrofitClient.getInstance()?.getApi()
        val data: MutableLiveData<Checkin> = MutableLiveData()
        CoroutineScope(Dispatchers.IO).launch {
            val response = api?.addNote(note)
            if(response!!.isSuccessful) {
                withContext(Dispatchers.Main) {
                    data.postValue(response.body())
                }
            }
        }
    }
}