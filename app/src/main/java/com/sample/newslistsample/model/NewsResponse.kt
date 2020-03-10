package com.nitish.newsapp.model

import com.google.gson.annotations.SerializedName
import com.sample.newslistsample.model.ArticlesItem

data class NewsResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)