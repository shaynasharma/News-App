package com.byju.news.di

import androidx.lifecycle.ViewModelProvider
import com.byju.news.model.NewsPaperDataSource
import com.byju.news.model.NewsPaperRepository
import com.byju.news.viewmodel.ViewModelFactory

/**
 * Created by Shayna Sharma on 12,June,2020
 */
object Injection {

    private val newsDataSource:NewsPaperDataSource = NewsPaperRepository()
    private val newsViewModelFactory = ViewModelFactory(newsDataSource)

    fun providerRepository():NewsPaperDataSource{
        return newsDataSource
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory{
        return newsViewModelFactory
    }
}