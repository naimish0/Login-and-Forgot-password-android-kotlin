package com.example.saveo.base


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hubco.ui.forgotpassword.ForgotPasswordViewModel
import com.example.hubco.ui.login.LoginViewActor
import com.example.hubco.ui.login.LoginViewModel
import com.example.saveo.data.remote.ApiCallMethods

/**
 * @AUTHOR Naimish Gupta
 * @date 05/01/2022
 * */

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val apiCallMethods: ApiCallMethods

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) ->
                    LoginViewModel(apiCallMethods)
                isAssignableFrom(ForgotPasswordViewModel::class.java)->
                    ForgotPasswordViewModel(apiCallMethods)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T

}