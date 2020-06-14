package com.byju.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byju.news.data.OperationCallback
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.model.NewsPaperDataSource
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsHomeViewModel(private val repository: NewsPaperDataSource):ViewModel() {

    private val _newsPaper = MutableLiveData<List<NewsPaper>>().apply { value = emptyList() }
    val news: LiveData<List<NewsPaper>> = _newsPaper

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    suspend fun loadNews() : LiveData<List<NewsPaper>> {
        _isViewLoading.postValue(true)

       return repository.retrieveNews()
    }

}