package com.byju.news.view

import android.content.Intent
import com.bumptech.glide.Glide
import com.byju.news.R
import com.byju.news.data.db.entities.NewsPaper
import com.byju.news.databinding.RowNewsBinding
import com.byju.news.utils.Utils
import com.xwray.groupie.databinding.BindableItem

class NewsItem(
    private val newspaper: NewsPaper
) : BindableItem<RowNewsBinding>(){

    override fun getLayout() = R.layout.row_news

    override fun bind(viewBinding: RowNewsBinding, position: Int) {
        viewBinding.newsTextView.text=newspaper.title
        viewBinding.newsCompanyNameTextView.text = newspaper.author
        viewBinding.newsPublishedDateTextView.text = Utils().convertToDatePatern(newspaper.publishedAt)
        Glide.with(viewBinding.newsImageView.context).load(newspaper.urlToImage).into(viewBinding.newsImageView)

        viewBinding.newsView.setOnClickListener {
            Intent(viewBinding.newsImageView.context, NewsDetailActivity::class.java).also {
                it.putExtra("Image", newspaper.urlToImage);
                it.putExtra("Title", newspaper.title);
                it.putExtra("Source", newspaper.author);
//                it.putExtra("Source", newspaper.source?.name);
                it.putExtra("Published", Utils().convertToDatePatern(newspaper.publishedAt));
                it.putExtra("Discription", newspaper.description);
                viewBinding.newsImageView.context.startActivity(it)
            }
        }

    }
}