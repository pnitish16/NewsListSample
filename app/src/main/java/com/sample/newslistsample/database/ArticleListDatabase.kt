package com.sample.newslistsample.database

import android.content.Context
import androidx.room.*
import com.sample.newslistsample.model.ArticlesItem

@Database(entities = [ArticlesItem::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleListDatabase : RoomDatabase() {

    abstract val articleListDao: ArticleListDao

    companion object {

        @Volatile
        private var INSTANCE: ArticleListDatabase? = null

        fun getInstance(context: Context): ArticleListDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ArticleListDatabase::class.java,
                        "article_list_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}