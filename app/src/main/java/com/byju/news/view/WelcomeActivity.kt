package com.byju.news.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.byju.news.R
import kotlinx.android.synthetic.main.activity_splash.*

class WelcomeActivity : AppCompatActivity() {
    val appWelcomeText="Byju's NewsPaper"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        appTitleTextView.text=appWelcomeText
        animateWelcomeView()
        callHomeActivity()
    }

    private fun animateWelcomeView() {
        rootlayout.animate().translationY(-2600f).setDuration(2000).startDelay = 2000
    }

    private fun callHomeActivity() {
        Handler().postDelayed({
            finish()
            overridePendingTransition(0, 0)
            Intent(this, HomeActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }
        }, 4000)
    }
}
