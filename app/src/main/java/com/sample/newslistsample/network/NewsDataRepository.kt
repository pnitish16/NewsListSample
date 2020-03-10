package com.sample.newslistsample.network

import com.nitish.newsapp.model.NewsResponse
import com.nitish.newsapp.network.NewsApi
import com.nitish.newsapp.network.NewsCatApi
import io.reactivex.Observable
class NewsDataRepository {

    fun getNewsFromNetwork(): Observable<NewsResponse> {
        val service =
            ServiceFactory.createRetrofitService(NewsCatApi::class.java, NewsApi.SERVICE_ENDPOINT)
        return service.getNews()
    }

}