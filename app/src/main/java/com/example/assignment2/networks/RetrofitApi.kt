package com.example.assignment2.networks

import com.example.assignment2.model.Checkin
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {
    @GET("checkin/")
    fun getAllNotes(): Call<List<Checkin>>
//    suspend fun getAllNotes(): Response<List<Note>>

    @POST("/checkin")
    suspend fun addNote(@Body checkin: Checkin): Response<Checkin>

}