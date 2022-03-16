package com.example.saveo.data.remote

import com.example.hubco.data.request.ForgotPasswordRequest
import com.example.hubco.data.request.LoginRequest
import com.example.hubco.data.response.ForgotPasswordResponse
import com.example.hubco.data.response.LoginResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @AUTHOR Naimish Gupta
 * @date 05/01/2022
 */
interface ApiCallMethods {

    @POST("/api/agent/v1/forget-password")
    fun forgotPassword(@Body forgotPasswordRequest: ForgotPasswordRequest): Single<ForgotPasswordResponse>

    @POST("/api/agent/sign-in")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}