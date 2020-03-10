package com.sample.newslistsample.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sample.newslistsample.database.ArticleListDao
import com.sample.newslistsample.model.ArticlesItem
import com.sample.newslistsample.network.NewsDataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class NewsListViewModel(
    val database: ArticleListDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val articles = database.getAllArticles()
    private var articleItem = MutableLiveData<ArticlesItem?>()
    private var newsDataRepository: NewsDataRepository
    var articleList = MutableLiveData<List<ArticlesItem?>>()

    init {
        intializeNews()
        newsDataRepository = NewsDataRepository()
    }

    private fun intializeNews() {
        uiScope.launch {
            articleItem.value = getArticlesDatabase()
        }
    }

    private suspend fun getArticlesDatabase(): ArticlesItem? {

        return withContext(Dispatchers.IO) {

            var newsArticlesItem = database.getArticleItem()
            newsArticlesItem
        }
    }


//    private suspend fun insert(articles: List<ArticlesItem?>) {
//        withContext(Dispatchers.IO) {
//            database.insertArticles(articles)
//        }
//    }

    private suspend fun update(articlesItem: ArticlesItem) {
        withContext(Dispatchers.IO) {
            database.updateArticle(articlesItem)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            articleItem.value = null
        }
    }

    suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    //getting all news
    @SuppressLint("CheckResult")
    fun getNewsArticle() {
        newsDataRepository.getNewsFromNetwork().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.status!! == "ok") {
                    articleList.value = it.articles
                } else {
                    articleList.value = null
                }
            },
                { error -> Log.d("error", error.message ?: "Error") })
    }

}