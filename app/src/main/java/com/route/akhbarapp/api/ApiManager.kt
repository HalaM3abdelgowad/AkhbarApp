package com.route.akhbarapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiManager {

    companion object{

        private var retrofit:Retrofit?=null

        //singlton pattern  in this fun //create one object from retrofit
        private fun getInstance():Retrofit{

            //create interceptor for help me to debug request
            val logging = HttpLoggingInterceptor(
                object :HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Log.e("api",message)
                    }

                }
            )
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            ///create retrofit
            if (retrofit==null){
                //create retrofit
                retrofit=Retrofit.Builder()
                    .client(client)
                    .baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }
            return retrofit!!

        }

        public fun getApis():WebServices{
            return getInstance().create(WebServices::class.java)
        }

    }
}