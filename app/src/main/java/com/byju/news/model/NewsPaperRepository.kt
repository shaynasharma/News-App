package com.byju.news.model

import android.util.Log
import com.byju.news.data.ApiClient
import com.byju.news.data.NewspaperResponse
import com.byju.news.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Shayna Sharma on 12,June,2020
 */
const val TAG="CONSOLE"

class NewsPaperRepository:NewsPaperDataSource {

    private var call:Call<NewspaperResponse>?=null

    override fun retrieveNews(callback: OperationCallback<NewsPaper>) {
        call=ApiClient.build()?.newspaper()
        call?.enqueue(object :Callback<NewspaperResponse>{
            override fun onFailure(call: Call<NewspaperResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<NewspaperResponse>, response: Response<NewspaperResponse>) {
                response.body()?.let {
                    if(response.isSuccessful && (it.isSuccess())){
                        Log.v(TAG, "data ${it.articles}")
                        callback.onSuccess(it.articles)
                    } else{
                        callback.onError(it.message)
                    }
                }
            }
        })
    }

    override fun cancel() {
        call?.let {
            it.cancel()
        }
    }
}