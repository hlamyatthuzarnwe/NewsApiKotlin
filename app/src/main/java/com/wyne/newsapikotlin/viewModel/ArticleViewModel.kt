package com.wyne.newsapikotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyne.newsapikotlin.api.ArticleApi
import com.wyne.newsapikotlin.model.ArticleResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//can't get data to view from viewModel
class ArticleViewModel : ViewModel(){

    //simple live data is inmutable declare with val
    var results : MutableLiveData<ArticleResult> = MutableLiveData()
    var articleLoadError : MutableLiveData<Boolean> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

    fun getResults() : LiveData<ArticleResult> = results
    fun getError() : LiveData<Boolean> = articleLoadError
    fun getLoading() : LiveData<Boolean> = loading

    private val articleApi : ArticleApi = ArticleApi()

    fun loadResults(){

        loading.value = true // initially to see loading when open app

        val call = articleApi.getResults()
        call.enqueue(object : retrofit2.Callback<ArticleResult>{
            override fun onFailure(call: Call<ArticleResult>, t: Throwable) {

                articleLoadError.value = true//error is occur
                loading.value = false


            }

            override fun onResponse(call: Call<ArticleResult>, response: Response<ArticleResult>) {

                response.isSuccessful.let {

                    loading.value = false
                    val resultList = ArticleResult(response?.body()?.articles ?: emptyList())//?: is ternary operator
                    //its return itself
                    results.value = resultList
                }

            }

        })
    }

}