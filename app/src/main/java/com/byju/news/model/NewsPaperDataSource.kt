package com.byju.news.model

import androidx.lifecycle.LiveData
import com.byju.news.data.OperationCallback
import com.byju.news.data.db.entities.NewsPaper

/**
 * Created by Shayna Sharma on 12,June,2020
 */
interface NewsPaperDataSource {
    suspend fun retrieveNews(): LiveData<List<NewsPaper>>
    fun cancel()
}