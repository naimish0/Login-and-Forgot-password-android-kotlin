package com.example.hubco.ui.login

import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.hubco.R
import com.example.hubco.base.BaseViewModel
import com.example.hubco.data.ErrorResponseHandler
import com.example.hubco.data.InternetThrowable
import com.example.hubco.data.request.LoginRequest
import com.example.hubco.data.response.LoginResponse
import com.example.hubco.utility.InternetUtil
import com.example.saveo.data.remote.ApiCallMethods
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class LoginViewModel(private val apiCallMethods: ApiCallMethods) :
    BaseViewModel<LoginViewActor>(apiCallMethods) {

    //region Api Handler Methods
     fun doLogin(emailMld:String,passwordMld:String) {
        if (InternetUtil.isInternetOn()) {
            val loginRequest = LoginRequest(
                email = emailMld,
                password = passwordMld
            )
            addDisposable(
                apiCallMethods.login(loginRequest)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<LoginResponse>() {
                        override fun onSuccess(response: LoginResponse) {
                            if (response.message == "SUCCESS") {
                                getViewActor().afterLoggedIn()
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

    //data binding methods

}