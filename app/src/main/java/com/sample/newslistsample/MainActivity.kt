package com.sample.newslistsample

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.newslistsample.adapter.MyArticlesItemRecyclerViewAdapter
import com.sample.newslistsample.database.ArticleListDao
import com.sample.newslistsample.database.ArticleListDatabase
import com.sample.newslistsample.model.ArticlesItem
import com.sample.newslistsample.viewmodel.NewsListViewModel
import com.sample.newslistsample.viewmodel.NewsListViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var newsListViewModel : NewsListViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var articleListDao: ArticleListDao
    private var articleList : List<ArticlesItem> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val application = requireNotNull(this).application

        articleListDao = ArticleListDatabase.getInstance(application).articleListDao

        val viewModelFactory = NewsListViewModelFactory(articleListDao, application)

        newsListViewModel =
            ViewModelProvider(this,viewModelFactory).get(NewsListViewModel::class.java)

        newsListViewModel.getNewsArticle()

        subscribeViewModel()
    }

    private fun subscribeViewModel() {

        articleListDao.getAllArticles().observe(this,Observer<List<ArticlesItem?>>{
            Log.d("Size", "" + it.size)
            linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            val adapter = MyArticlesItemRecyclerViewAdapter(this, it)
            rvNewsList.layoutManager = linearLayoutManager
            rvNewsList.adapter = adapter
        })

//        newsListViewModel.articleList.observe(this, Observer<List<ArticlesItem?>> {
//            if(it!= null){
//                AsyncTask.execute {
//                    for(i in it.indices) {
//                        Log.d("index",""+ i)
//                        articleListDao.insertArticle(it[i]!!)
//                    }
//                }
//            }
//        })
    }
}
