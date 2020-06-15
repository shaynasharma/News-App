package com.byju.news.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.byju.news.model.NewsSource
import java.io.Serializable

/**
 * Created by Shayna Sharma on 12,June,2020
 */
@Entity
data class NewsPaper(
    @PrimaryKey(autoGenerate = false)
    val publishedAt:String,
    val author:String?,
    val title:String,
    val description:String?,
    val urlToImage:String?)