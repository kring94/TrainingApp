package com.example.calculator.horoscopeapp.data.network

import com.example.calculator.horoscopeapp.data.network.model.HoroscopeResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface HoroscopeApi {

    @POST(".")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("Day") day: String
    ): Response<HoroscopeResponse>
}