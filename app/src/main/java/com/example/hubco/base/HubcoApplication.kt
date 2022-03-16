package com.example.saveo.base

import android.app.Application
import com.example.hubco.utility.InternetUtil
import com.example.saveo.data.remote.ApiCallMethods
import com.example.saveo.data.remote.ApiHandler

/**
 * @AUTHOR Naimish Gupta
 * @date 05/01/2022
 */
class HubcoApplication : Application() {
    //region Companion Object
    companion object {
        private var app: HubcoApplication? = null
        val apiCallMethods: ApiCallMethods get() = ApiHandler.getInstance().handler

    }

    override fun onCreate() {
        super.onCreate()
        //init internet utils
        InternetUtil.init(this)
        app = this
    }
}