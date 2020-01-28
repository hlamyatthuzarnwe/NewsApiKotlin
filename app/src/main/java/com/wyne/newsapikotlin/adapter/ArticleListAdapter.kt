package com.wyne.newsapikotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wyne.newsapikotlin.R
import com.wyne.newsapikotlin.model.Article
import com.wyne.newsapikotlin.toSimpleString
import kotlinx.android.synthetic.main.item_article_list.view.*

class ArticleListAdapter(var articleList: List<Article> = ArrayList()) : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>(){

    var mClickListener : ClickListener? = null//for interface to call from onClick()

    fun setOnClickListener(clickListener: ClickListener){
        this.mClickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_article_list,parent,false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
       return articleList.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindArticle(articleList.get(position))
    }

    fun updateList(article : List<Article>){
        this.articleList = article
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{

        private var view : View = itemView
        private lateinit var article : Article

        init {
            itemView.setOnClickListener(this)//this intend to interface
        }

        fun bindArticle(article: Article){

            this.article = article

            Picasso.get().load(article.urlToImage).placeholder(R.drawable.loading).into(view.img_article)
            view.txt_article_title.text = article.title
            view.txt_article_description.text = article.description
            view.txt_article_date.text = toSimpleString(article.publishedAt)
        }

        override fun onClick(v: View?) {

            mClickListener?.onClick(article)

        }

    }

    interface ClickListener{
        fun onClick(article : Article)
    }

}