package com.flores.dummydictionary.data.network

import com.flores.dummydictionary.data.network.dto.LoginRequest
import com.flores.dummydictionary.data.network.dto.LoginResponse
import com.flores.dummydictionary.data.network.dto.WordsResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WordService {
    @GET("/words")
    suspend fun getAllWord(): WordsResponse

    @POST("/login")
    suspend fun login(@Body credentials: LoginRequest): LoginResponse
}