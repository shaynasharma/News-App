package com.byju.news.view

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.byju.news.R
import kotlinx.android.synthetic.main.activity_news_detail.*
import kotlinx.android.synthetic.main.activity_news_home.*


/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein_anim)

        detail_view.startAnimation(fadeIn)
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
