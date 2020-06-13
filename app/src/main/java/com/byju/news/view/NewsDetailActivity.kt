package com.byju.news.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.byju.news.R
import com.byju.news.di.Injection
import com.byju.news.model.NewsPaper
import com.byju.news.utils.Utils
import com.byju.news.viewmodel.NewsHomeViewModel
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_home.*
import kotlinx.android.synthetic.main.layout_error.*


/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        setupUI()
    }

    //ui
    private fun setupUI(){
        val arguments =
            requireNotNull(intent?.extras) { "There should be parameters or your more meaningful message." }

        with(arguments) {
            newsTitleTextView.text = getString("Title");
            newsSourceTextView.text = getString("Source");
            newsPublishedDateTextView.text = getString("Published");
            newsDescription.text = getString("Discription");

            Glide.with(newsPaperImageView.context).load(getString("Image")).into(newsPaperImageView)
        }
    }

}
