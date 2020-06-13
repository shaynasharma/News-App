package com.byju.news.model

import com.byju.news.data.OperationCallback

/**
 * Created by Shayna Sharma on 12,June,2020
 */
interface NewsPaperDataSource {
    fun retrieveNews(callback: OperationCallback<NewsPaper>)
    fun cancel()
}