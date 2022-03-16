package com.example.hubco.ui.forgotpassword

import android.util.Log
import com.example.hubco.base.BaseViewModel
import com.example.hubco.data.ErrorResponseHandler
import com.example.hubco.data.InternetThrowable
import com.example.hubco.data.request.ForgotPasswordRequest
import com.example.hubco.data.request.LoginRequest
import com.example.hubco.data.response.ForgotPasswordResponse
import com.example.hubco.data.response.LoginResponse
import com.example.hubco.utility.InternetUtil
import com.example.saveo.data.remote.ApiCallMethods
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class ForgotPasswordViewModel(private val apiCallMethods: ApiCallMethods) :
    BaseViewModel<ForgotPasswordViewActor>(apiCallMethods){

    //region Api Handler Methods
    fun forgotPassword(username:String,dob:String) {
        if (InternetUtil.isInternetOn()) {
            val loginRequest = ForgotPasswordRequest(
                email = username,
                dob = dob
            )
            addDisposable(
                apiCallMethods.forgotPassword(loginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<ForgotPasswordResponse>() {
                        override fun onSuccess(response: ForgotPasswordResponse) {
                            if (response.message == "SUCCESS") {
                                getViewActor().afterPasswordReset()
                            } else {
                                Log.d("TAG", response.message)
                            }
                        }

                        override fun onError(e: Throwable) {
                            if (e is HttpException) {
                                try {
                                    val errorBody = Gson().fromJson(
                                        e.response()?.errorBody()?.string(),
                                        ErrorResponseHandler::class.java
                                    )
                                } catch (e: Exception) {

                                }

                            } else {
                                getViewActor().onApiError(e)
                            }
                        }
                    })
            )
        } else {
            getViewActor().onApiError(InternetThrowable())
        }
    }
    //endregion
    }