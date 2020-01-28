package com.wyne.newsapikotlin.fragment


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.wyne.newsapikotlin.R
import com.wyne.newsapikotlin.model.ArticleResult
import com.wyne.newsapikotlin.adapter.ArticleListAdapter
import com.wyne.newsapikotlin.model.Article
import com.wyne.newsapikotlin.viewModel.ArticleViewModel
import com.wyne.newsapikotlin.viewModel.SelectedArticleViewModel
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticleListFragment : Fragment(), ArticleListAdapter.ClickListener{

    private lateinit var articleListAdapter: ArticleListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var articleViewModel : ArticleViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewManager = LinearLayoutManager(activity)
        articleListAdapter =
            ArticleListAdapter()
        rv_article_list.adapter = articleListAdapter
        rv_article_list.layoutManager = viewManager

        articleListAdapter.setOnClickListener(this)
        observerViewModel()

    }

    fun observerViewModel(){

        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)//of(this) must be lifecycle owner;of() is fragment
        articleViewModel.getResults().observe(this, Observer <ArticleResult>{result ->
            rv_article_list.visibility = View.VISIBLE
            articleListAdapter.updateList(result.articles)
        })

        articleViewModel.getError().observe(this,Observer<Boolean>{ isError ->
            if (isError){
                txt_error.visibility = View.VISIBLE
                rv_article_list.visibility = View.GONE
            }else{
                txt_error.visibility = View.GONE
            }
        })

        articleViewModel.getLoading().observe(this,Observer<Boolean>{isLoading ->
            loading_view.visibility = (if(isLoading) View.VISIBLE else View.INVISIBLE)
            if(isLoading){
                txt_error.visibility = View.GONE
                rv_article_list.visibility = View.GONE
            }

        })
    }

    override fun onResume() {
        super.onResume()
        articleViewModel.loadResults()
    }

    override fun onClick(article: Article) {//comes out from adapter of interface
        if (!TextUtils.isEmpty(article.url)){
            val selectedArticleViewModel : SelectedArticleViewModel = ViewModelProviders.of(activity!!).get(SelectedArticleViewModel::class.java)
            selectedArticleViewModel.selectedAtricle(article)
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.screen_container,DetailsFragment()).addToBackStack(null).commit()//addToBackStack(null) is to press back key from phone
        }

    }

}
