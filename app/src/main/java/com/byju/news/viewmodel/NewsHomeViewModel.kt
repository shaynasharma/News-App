package com.byju.news.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.byju.news.data.OperationCallback
import com.byju.news.model.NewsPaper
import com.byju.news.model.NewsPaperDataSource

class NewsHomeViewModel(private val repository: NewsPaperDataSource):ViewModel() {

    private val _newsPaper = MutableLiveData<List<NewsPaper>>().apply { value = emptyList() }
    val news: LiveData<List<NewsPaper>> = _newsPaper

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    /*
    If you require that the data be loaded only once, you can consider calling the method
    "loadMuseums()" on constructor. Also, if you rotate the screen, the service will not be called.

    init {
        //loadMuseums()
    }
     */

    fun loadMuseums(){
        _isViewLoading.postValue(true)
        repository.retrieveMuseums(object:OperationCallback<NewsPaper>{
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