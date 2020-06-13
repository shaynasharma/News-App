package com.byju.news.data

import com.byju.news.model.NewsPaper

data class MuseumResponse(val status:String?,val code:String?,val message:String?,val totalResults:Int?,val articles:List<NewsPaper>?){
    fun isSuccess():Boolean= (status=="ok")
    fun isError():Boolean= (status=="error")
}