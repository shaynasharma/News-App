package com.byju.news.view

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.byju.news.R
import kotlinx.android.synthetic.main.activity_news_detail.*


/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein_anim)
        newsPaperImageView.startAnimation(fadeIn)
        setupUI()
    }

    //ui
    private fun setupUI(){
        backImageView.setOnClickListener {
            onBackPressed()
        }
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
