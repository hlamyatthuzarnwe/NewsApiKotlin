package com.wyne.newsapikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wyne.newsapikotlin.fragment.ArticleListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val articleFragment =
                ArticleListFragment()
            supportFragmentManager.beginTransaction().add(R.id.screen_container,articleFragment).commit()
        }
    }
}
