package com.route.akhbarapp.api

import com.route.akhbarapp.model.NewsResponse
import com.route.akhbarapp.model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    @GET/*type of request*/("v2/top-headlines/sources"/*url of function*/)
    fun getSource(
        @Query("apiKey"/*the same name in post man*/)apiKey:String
    ):Call<SourcesResponse>

    @GET("v2/everything")
    fun getNews(
        @Query("apiKey") apiKey:String,
        @Query("sources") sources:String
    ):Call<NewsResponse>
}