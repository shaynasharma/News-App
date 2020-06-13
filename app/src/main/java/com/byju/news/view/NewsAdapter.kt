package com.byju.news.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.byju.news.R
import com.byju.news.Utils
import com.byju.news.model.NewsPaper
import kotlinx.android.synthetic.main.row_news.view.*

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
        fun bind(museum:NewsPaper){
            news.text = museum.title
            newsCompanyName.text = museum.source?.name
            newsPublishedDate.text = Utils().convertToFormatDate(museum.publishedAt)
            Glide.with(newsImage.context).load(museum.urlToImage).into(newsImage)
        }
    }
}