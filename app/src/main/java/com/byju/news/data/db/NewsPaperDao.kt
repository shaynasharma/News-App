package com.byju.news.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.byju.news.data.db.entities.NewsPaper

@Dao
interface NewsPaperDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllNews(quotes : List<NewsPaper>)

    @Query("SELECT * FROM NewsPaper")
    fun getNews() : LiveData<List<NewsPaper>>

}