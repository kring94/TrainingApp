package com.example.calculator.superheroapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/api/6594334677248669/search/{name}")
    suspend fun getSuperheros(@Path("name") superheroName:String):Response<SuperHeroDataResponse>

}