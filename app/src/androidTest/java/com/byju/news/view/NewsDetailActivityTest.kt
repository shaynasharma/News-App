package com.byju.news.view

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.byju.news.R
import com.byju.news.data.db.entities.NewsPaper
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Created by Shayna Sharma on 15,June,2020
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class NewsDetailActivityTest {

    @Test
    fun is_ActivityInView() {
        ActivityScenario.launch(NewsDetailActivity::class.java)
        onView(withId(R.id.detail_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_recreateActivity() {
        // SETUP
        val publishedAt = "15-06-2020"
        val author = "Julien Ponthus"
        val title = "Stocks, oil fall as second wave fears cloud recovery prospects - Reuters"
        val description = "Fears that a second wave of COVID-19 infections is under way sent jitters across global markets on Monday"
        val newsUrlImage = "https://s4.reutersmedia.net/resources/r/?m=02&d=20200615&t=2&i=1522244693&w=1200&r=LYNXMPEG5E0JR"


        val newspaper = NewsPaper(
            publishedAt,
            author,
            title,
            description ,
            newsUrlImage
        )

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, NewsDetailActivity::class.java)
        intent.putExtra("Title",newspaper.title)
        intent.putExtra("Source",newspaper.author)
        intent.putExtra("Published",newspaper.publishedAt)
        intent.putExtra("Discription",newspaper.description)
        intent.putExtra("Image",newspaper.urlToImage)
        targetContext.startActivity(intent)


        val arguments =
            requireNotNull(intent.extras)

        // VERIFY
        onView(withId(R.id.newsTitleTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Title"))))

        onView(withId(R.id.newsPaperImageView))
            .check(matches(ViewMatchers.withText(arguments.getString("Image"))))

        onView(withId(R.id.newsSourceTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Source"))))

        onView(withId(R.id.newsDescription))
            .check(matches(ViewMatchers.withText(arguments.getString("Discription"))))

        onView(withId(R.id.newsPublishedDateTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Published"))))

    }

    @Test
    fun test_isMovieDataVisible() {
        // SETUP
        val publishedAt = "15-06-2020"
        val author = "Julien Ponthus"
        val title = "Stocks, oil fall as second wave fears cloud recovery prospects - Reuters"
        val description = "Fears that a second wave of COVID-19 infections is under way sent jitters across global markets on Monday"
        val newsUrlImage = "https://s4.reutersmedia.net/resources/r/?m=02&d=20200615&t=2&i=1522244693&w=1200&r=LYNXMPEG5E0JR"


        val newspaper = NewsPaper(
            publishedAt,
            author,
            title,
            description ,
            newsUrlImage
        )

        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val intent = Intent(targetContext, NewsDetailActivity::class.java)
        intent.putExtra("Title",newspaper.title)
        intent.putExtra("Source",newspaper.author)
        intent.putExtra("Published",newspaper.publishedAt)
        intent.putExtra("Discription",newspaper.description)
        intent.putExtra("Image",newspaper.urlToImage)
        targetContext.startActivity(intent)


        val arguments =
            requireNotNull(intent.extras)

        // VERIFY
        onView(withId(R.id.newsTitleTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Title"))))

        onView(withId(R.id.newsPaperImageView))
            .check(matches(ViewMatchers.withText(arguments.getString("Image"))))

        onView(withId(R.id.newsSourceTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Source"))))

        onView(withId(R.id.newsDescription))
            .check(matches(ViewMatchers.withText(arguments.getString("Discription"))))

        onView(withId(R.id.newsPublishedDateTextView))
            .check(matches(ViewMatchers.withText(arguments.getString("Published"))))

    }

}