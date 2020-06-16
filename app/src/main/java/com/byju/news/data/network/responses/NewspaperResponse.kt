package com.byju.news.data.network.responses

import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.model.NewsSource

/**
 * Created by Shayna Sharma on 12,June,2020
 */
data class NewspaperResponse(
    val status:String?,
    val code:String?,
    val message:String?,
    val totalResults:Int?,
    val articles:List<NewsPaper>?)