package com.example.assignment2.networks

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//https://639bcc3d64fcf9c11ca35424.mockapi.io/:endpoint

//const val BASE_URL = "https://6395979090ac47c6806e78c0.mockapi.io/"
const val BASE_URL = "https://639bcc3d64fcf9c11ca35424.mockapi.io/"
// build connection to REST API
class RetrofitClient {
    companion object {
        private var INSTANCE: RetrofitClient? = null
        private var api: RetrofitApi? = null

        @Synchronized
        fun getInstance(): RetrofitClient? {
            if(INSTANCE == null) {
                INSTANCE = RetrofitClient()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

                api = retrofit.create(RetrofitApi::class.java)
            }
            return INSTANCE
        }

    }

    fun getApi(): RetrofitApi? {
        return api
    }
}