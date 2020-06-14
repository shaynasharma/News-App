package com.byju.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.byju.news.R
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.viewmodel.NewsHomeViewModel
import com.byju.news.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_news_home.*
import kotlinx.android.synthetic.main.layout_error.*
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

/**
 * Created by Shayna Sharma on 12,June,2020
 */
class NewsHomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : ViewModelFactory by instance()
    private lateinit var viewModel: NewsHomeViewModel
    private lateinit var adapter: NewsAdapter

    companion object {
        const val TAG= "CONSOLE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_home)
        setupViewModel()
        setupUI()
    }

    //ui
    private fun setupUI(){
        adapter= NewsAdapter(viewModel.news.value?: emptyList())
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }

    private fun setupViewModel(){
        viewModel = ViewModelProviders.of(this, factory).get(NewsHomeViewModel::class.java)
        viewModel.news.observe(this,renderNews)

        viewModel.isViewLoading.observe(this,isViewLoadingObserver)
        viewModel.onMessageError.observe(this,onMessageErrorObserver)
        viewModel.isEmptyList.observe(this,emptyListObserver)
    }

    //observers
    private val renderNews= Observer<List<NewsPaper>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility=View.GONE
        layoutEmpty.visibility=View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver= Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility=if(it)View.VISIBLE else View.GONE
        progressBar.visibility= visibility
    }

    private val onMessageErrorObserver= Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility=View.VISIBLE
        layoutEmpty.visibility=View.GONE
        textViewError.text= "Error $it"
    }

    private val emptyListObserver= Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutEmpty.visibility=View.VISIBLE
        layoutError.visibility=View.GONE
    }

     //If you require updated data, you can call the method "loadNews" here
     override fun onResume() {
        super.onResume()
         Coroutines.main {
             viewModel.loadNews()
         }
     }

}
