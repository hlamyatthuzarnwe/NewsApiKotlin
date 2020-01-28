package com.wyne.newsapikotlin.api

import com.wyne.newsapikotlin.model.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ArticleInterface {

    //X-Api-Key and : must not include space
    @Headers("X-Api-Key: 313228e924904cb6a0a1fe355ba79cf2")//When apiKey add in @Header(), not necessary to write query.
    @GET("top-headlines?country=us")
    fun getArticles() :  Call<ArticleResult>
}