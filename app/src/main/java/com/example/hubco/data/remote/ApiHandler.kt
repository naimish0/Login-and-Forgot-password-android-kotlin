package com.example.saveo.data.remote


import com.example.hubco.utility.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @Author Naimish Gupta
 * @date   05/01/2022
 */
class ApiHandler private constructor(baseUrl: String) {

    val handler: ApiCallMethods

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        //create okhttp client
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(25, TimeUnit.SECONDS)
            .build()
        // create retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        handler = retrofit.create<ApiCallMethods>(ApiCallMethods::class.java)
    }

    companion object {
        private var instance: ApiHandler? = null

        /**
         * Gets instance.
         * @return the instance of APIHandler
         */
        fun getInstance(): ApiHandler {
            if (instance == null) {
                synchronized(ApiHandler::class.java) {
                    if (instance == null) {
                        instance =
                            ApiHandler(Constants.BASE_URL)
                    }
                }
            }
            return instance!!
        }
    }


}
