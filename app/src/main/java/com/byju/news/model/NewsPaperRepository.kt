package com.byju.news.model

import android.util.Log
import com.byju.news.data.ApiClient
import com.byju.news.data.MuseumResponse
import com.byju.news.data.OperationCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val TAG="CONSOLE"

class MuseumRepository:NewsPaperDataSource {

    private var call:Call<MuseumResponse>?=null

    override fun retrieveMuseums(callback: OperationCallback<NewsPaper>) {
        call=ApiClient.build()?.museums()
        call?.enqueue(object :Callback<MuseumResponse>{
            override fun onFailure(call: Call<MuseumResponse>, t: Throwable) {
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<MuseumResponse>, response: Response<MuseumResponse>) {
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