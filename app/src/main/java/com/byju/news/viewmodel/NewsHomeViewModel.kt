package com.byju.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.data.repositories.NewsRepository
import net.simplifiedcoding.mvvmsampleapp.util.lazyDeferred

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsHomeViewModel(repository: NewsRepository
) : ViewModel() {

    val news by lazyDeferred {
        repository.getNews()
    }
}