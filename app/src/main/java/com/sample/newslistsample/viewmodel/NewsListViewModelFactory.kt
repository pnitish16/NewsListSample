package com.sample.newslistsample.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.newslistsample.database.ArticleListDao
import com.sample.newslistsample.database.ArticleListDatabase

@Suppress("UNCHECKED_CAST")
class NewsListViewModelFactory(
    private val articleDao: ArticleListDao,
    private val application: Application
) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
            return NewsListViewModel(articleDao,application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}