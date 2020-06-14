package com.byju.news.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.byju.news.data.ApiClient
import com.byju.news.data.NewspaperResponse
import com.byju.news.data.OperationCallback
import com.byju.news.data.db.AppDatabase
import com.byju.news.data.db.entities.NewsPaper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Shayna Sharma on 12,June,2020
 */
const val TAG="CONSOLE"

class NewsPaperRepository(private val db: AppDatabase):NewsPaperDataSource {
    private val news = MutableLiveData<List<NewsPaper>>()

    private var call:Call<NewspaperResponse>?=null

    init {
        news.observeForever {
            saveQuotes(it)
        }
    }

    override suspend fun retrieveNews(): LiveData<List<NewsPaper>> {
        call=ApiClient.build()?.getNewspaper()
        call?.enqueue(object :Callback<NewspaperResponse>{
            override fun onFailure(call: Call<NewspaperResponse>, t: Throwable) {
            }

            override fun onResponse(call: Call<NewspaperResponse>, response: Response<NewspaperResponse>) {
                response.body()?.let {
                    if(response.isSuccessful && (it.isSuccess())){
                        Log.v(TAG, "data ${it.articles}")
                        news.postValue(it.articles)
                    }
                }
            }
        })
        return withContext(Dispatchers.IO) {
            db.getNewsPaperDao().getNews()
        }
    }

    private fun saveQuotes(newsPaper: List<NewsPaper>) {
        Coroutines.io {
            db.getNewsPaperDao().saveAllNews(newsPaper)
        }
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}