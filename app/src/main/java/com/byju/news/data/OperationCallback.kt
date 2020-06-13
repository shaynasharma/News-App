package com.byju.news.data

/**
 * Created by Shayna Sharma on 12,June,2020
 */
interface OperationCallback<T> {
    fun onSuccess(data:List<T>?)
    fun onError(error:String?)
}