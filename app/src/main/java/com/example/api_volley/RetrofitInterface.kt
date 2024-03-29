package com.example.api_volley

import retrofit2.Response
import retrofit2.http.GET

interface RetrofitInterface {

    @GET("/users")
    suspend fun getDetailsRetrofit(): Response<UserInfo>
}