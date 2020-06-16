package com.byju.news.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.navigation.Navigation
import com.byju.news.R
import com.byju.news.utils.snackbar
import kotlinx.android.synthetic.main.activity_news_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)

       Navigation.findNavController(this, R.id.fragment)
    }

    override fun onBackPressed() {
        roothomeView.snackbar("Hey, Back Button Pressed Byju's NewsPaper is closing now, See you Soon.")
        Handler().postDelayed({
            super.onBackPressed()
        },4000)
    }

}
