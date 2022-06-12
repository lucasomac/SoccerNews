package br.com.lucolimac.soccernews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.soccernews.databinding.NewsItemBinding
import br.com.lucolimac.soccernews.domain.News

class NewsAdapter(private var newsData: MutableList<News>) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        return NewsHolder(
            NewsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        with(holder.binding) {
            with(newsData[position]) {
                textViewTitle.text = title
                textViewDescription.text = description
            }
        }
    }

    override fun getItemCount(): Int {
        return this.newsData.size
    }

    fun setNewsData(list: MutableList<News>) {
        this.newsData = list
    }

    inner class NewsHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root)
}