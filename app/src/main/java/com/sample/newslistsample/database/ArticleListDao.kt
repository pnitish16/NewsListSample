package com.sample.newslistsample.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.sample.newslistsample.model.ArticlesItem

@Dao
interface ArticleListDao : BaseDao<ArticlesItem>{

//    @Insert
//    fun insertArticles(vararg articles: List<ArticlesItem?>)

    @Insert
    fun insertArticle(article: ArticlesItem)

    @Update
    fun updateArticle(article: ArticlesItem)

    @Query("SELECT * from article_list_table WHERE articleId = :key")
    fun getArticle(key: Long): ArticlesItem?

    @Query("DELETE FROM article_list_table")
    fun clear()

    @Query("SELECT * FROM article_list_table ORDER BY articleId DESC LIMIT 1")
    fun getArticleItem(): ArticlesItem?

    @Query("SELECT * FROM article_list_table ORDER BY articleId DESC")
    fun getAllArticles(): LiveData<List<ArticlesItem>>
}