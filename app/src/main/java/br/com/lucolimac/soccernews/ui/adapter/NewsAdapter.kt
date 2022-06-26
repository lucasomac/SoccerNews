package br.com.lucolimac.soccernews.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.lucolimac.soccernews.R
import br.com.lucolimac.soccernews.databinding.NewsItemBinding
import br.com.lucolimac.soccernews.domain.News
import com.squareup.picasso.Picasso

class NewsAdapter(
    private var newsData: List<News>,
    private val onFavoriteListener: (News) -> Unit
) :
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
            val context = holder.itemView.context
            with(newsData[position]) {
                textViewTitle.text = title
                textViewDescription.text = description
                Picasso.get().load(image).fit().into(imageViewPhoto)
                buttonOpenLink.setOnClickListener {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(link)
                        )
                    )
                }
                if (newsData[position].favorite) {
                    icFavorite.setColorFilter(context.resources.getColor(R.color.icon_active))
                } else {
                    icFavorite.setColorFilter(context.resources.getColor(R.color.icon_inactive))
                }
                icFavorite.setOnClickListener {
                    newsData[position].favorite = !newsData[position].favorite
                    onFavoriteListener(newsData[position])
                    notifyItemChanged(position)
                }
                icShare.setOnClickListener {
                    holder.itemView.context.startActivity(
                        Intent.createChooser(
                            Intent(
                                Intent.ACTION_SEND,
                                Uri.parse(link)
                            ).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, description + "\n" + link)
                                putExtra(Intent.EXTRA_SUBJECT, title)
                            }, "Share via"
                        )
                    )
                }
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