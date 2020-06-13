package com.byju.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byju.news.data.OperationCallback
import com.byju.news.model.NewsPaper
import com.byju.news.model.NewsPaperDataSource

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

    fun loadNews(){
        _isViewLoading.postValue(true)
        repository.retrieveNews(object:OperationCallback<NewsPaper>{
            override fun onError(error: String?) {
                _isViewLoading.postValue(false)
                _onMessageError.postValue( error)
            }

            override fun onSuccess(data: List<NewsPaper>?) {
                _isViewLoading.postValue(false)

                if(data!=null){
                    if(data.isEmpty()){
                        _isEmptyList.postValue(true)
                    }else{
                        _newsPaper.value= data
                    }
                }
            }
        })
    }

}