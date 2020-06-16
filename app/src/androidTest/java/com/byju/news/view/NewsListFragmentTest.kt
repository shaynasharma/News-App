package com.byju.news.view

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.byju.news.R
import com.byju.news.data.db.entities.NewsPaper
import org.hamcrest.CoreMatchers.not
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4ClassRunner::class)
class NewsListFragmentTest{
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

    @Test
    fun a_test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(click())

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.newsTextView)).check(matches(withText(newspaper.title)))
    }

    @Test
    fun test_backNavigation_toMovieListFragment() {

        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(click())

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.newsTextView)).check(matches(withText(newspaper.title)))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}









