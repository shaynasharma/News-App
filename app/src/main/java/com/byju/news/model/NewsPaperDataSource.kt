package com.byju.news.model

import com.byju.news.data.OperationCallback

interface NewsPaperDataSource {

    fun retrieveMuseums(callback: OperationCallback<NewsPaper>)
    fun cancel()
}