package com.byju.news.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.byju.news.R
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.utils.Coroutines
import com.byju.news.utils.hide
import com.byju.news.utils.show
import com.byju.news.viewmodel.NewsHomeViewModel
import com.byju.news.viewmodel.ViewModelFactory
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.news_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsHomeFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: ViewModelFactory by instance()

    private lateinit var viewModel: NewsHomeViewModel

    companion object {
        const val TAG= "CONSOLE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(NewsHomeViewModel::class.java)
        bindUI()
    }


    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.news.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toNewsItem())
        })
    }

    private fun initRecyclerView(newsItem: List<NewsItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(newsItem)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }


    private fun List<NewsPaper>.toNewsItem() : List<NewsItem>{
        return this.map {
            NewsItem(it)
        }
    }

}
