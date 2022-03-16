package com.example.hubco.data.request


import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(
    @SerializedName("username")
    val email: String,
    @SerializedName("dob")
    val dob:String
)