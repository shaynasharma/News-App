package com.byju.news.view

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byju.news.R
import com.byju.news.utils.Utils
import com.byju.news.model.NewsPaper
import kotlinx.android.synthetic.main.row_news.view.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * Created by Shayna Sharma on 12,June,2020
 */
const val TAG="CONSOLE"

class NewsAdapter(private var news:List<NewsPaper>):RecyclerView.Adapter<NewsAdapter.MViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_news, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        //render
        vh.bind(news[position])
    }

    override fun getItemCount(): Int {
        return news.size
    }

    fun update(data:List<NewsPaper>){
        news= data
        notifyDataSetChanged()
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val news:TextView = view.newsTextView
        private val newsCompanyName:TextView = view.newsCompanyNameTextView
        private val newsImage:ImageView = view.newsImageView
        private val newsPublishedDate:TextView = view.newsPublishedDateTextView
        fun bind(newspaper:NewsPaper){
            news.text = newspaper.title
            newsCompanyName.text = newspaper.source?.name
            newsPublishedDate.text = Utils().convertToDatePatern(newspaper.publishedAt)
            Glide.with(newsImage.context).load(newspaper.urlToImage).into(newsImage)

            itemView.onClick {
                Log.e(TAG,"Item clicked : $newspaper")
                Intent(itemView.context, NewsDetailActivity::class.java).also {
                    it.putExtra("Image", newspaper.urlToImage);
                    it.putExtra("Title", newspaper.title);
                    it.putExtra("Source", newspaper.source?.name);
                    it.putExtra("Published", Utils().convertToDatePatern(newspaper.publishedAt));
                    it.putExtra("Discription", newspaper.description);
                    itemView.context.startActivity(it)
                }
            }

        }
    }
}