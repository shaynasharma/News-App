package com.byju.news.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byju.news.data.db.AppDatabase
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.data.network.MyApi
import com.byju.news.data.network.SafeApiRequest
import com.byju.news.utils.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

private val MINIMUM_INTERVAL = 6

class NewsRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    private val news = MutableLiveData<List<NewsPaper>>()

    init {
        news.observeForever {
            saveNews(it)
        }
    }

    suspend fun getNews(): LiveData<List<NewsPaper>> {
        return withContext(Dispatchers.IO) {
            fetchNews()
            db.getNewsPaperDao().getNews()
        }
    }

    private suspend fun fetchNews() {
            try {
                val response = apiRequest { api.getNewspaper() }
                news.postValue(response.articles)
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }

    private fun saveNews(newsPaper: List<NewsPaper>) {
        Coroutines.io {
            db.getNewsPaperDao().saveAllNews(newsPaper)
        }
    }

}