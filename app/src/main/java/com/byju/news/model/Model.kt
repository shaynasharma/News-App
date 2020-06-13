package com.byju.news.model

import java.io.Serializable

data class NewsPaper(val author:String?, val title:String, val description:String?, val urlToImage:String?, val publishedAt:String?, val source:NewsSource?):Serializable
data class NewsSource(val id:String?,val name:String?)