package com.byju.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.byju.news.model.NewsPaperDataSource

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class ViewModelFactory(private val repository:NewsPaperDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsHomeViewModel(repository) as T
    }
}