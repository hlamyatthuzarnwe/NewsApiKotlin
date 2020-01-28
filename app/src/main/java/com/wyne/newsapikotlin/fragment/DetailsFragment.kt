package com.wyne.newsapikotlin.fragment


import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.wyne.newsapikotlin.R
import com.wyne.newsapikotlin.model.Article
import com.wyne.newsapikotlin.viewModel.SelectedArticleViewModel
import kotlinx.android.synthetic.main.fragment_details.*


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val selectedArticleViewModel : SelectedArticleViewModel = ViewModelProviders.of(activity!!).get(SelectedArticleViewModel::class.java)
        selectedArticleViewModel.getSelectedArticle().observe(this, Observer <Article>{ article ->
            webView_fragmentDetails.loadUrl(article.url)

        })

        webView_fragmentDetails.webViewClient = object : WebViewClient(){

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                loadingArticle_fragmentDetails.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                loadingArticle_fragmentDetails.visibility = View.INVISIBLE
            }

        }
    }
}
