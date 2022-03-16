package com.example.hubco.data.response


import com.google.gson.annotations.SerializedName

data class ForgotPasswordResponse(
    @SerializedName("status")
    val message: String
)