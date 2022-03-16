package com.example.hubco.data.response


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status")
    val message: String
)