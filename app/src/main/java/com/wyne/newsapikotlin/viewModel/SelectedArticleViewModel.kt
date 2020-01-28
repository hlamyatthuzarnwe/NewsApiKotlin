package com.wyne.newsapikotlin.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wyne.newsapikotlin.model.Article

class SelectedArticleViewModel : ViewModel() {

    private var selectedArticle : MutableLiveData<Article> = MutableLiveData()

    fun getSelectedArticle() : LiveData<Article> = selectedArticle

    fun selectedAtricle(article: Article){
        selectedArticle.value = article
    }

}