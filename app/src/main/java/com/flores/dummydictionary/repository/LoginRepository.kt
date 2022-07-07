package com.flores.dummydictionary.repository

import com.flores.dummydictionary.data.network.ApiResponse
import com.flores.dummydictionary.data.network.WordService
import com.flores.dummydictionary.data.network.dto.LoginRequest
import retrofit2.HttpException
import java.io.IOException

class LoginRepository(private val api: WordService) {
    suspend fun login(username : String, password: String): ApiResponse<String>{
        try {

            val response = api.login(LoginRequest(username, password))
            return ApiResponse.Success(response.token)
        } catch (e: HttpException){
            if(e.code()== 400){
                return ApiResponse.ErrorWithMessage(e.response()?.body().toString())
            }
            return ApiResponse.Error(e)
        }catch (e:IOException){
            return ApiResponse.Error(e)
        }
    }
}