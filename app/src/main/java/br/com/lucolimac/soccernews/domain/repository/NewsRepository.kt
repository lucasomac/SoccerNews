package br.com.lucolimac.soccernews.domain.repository

import br.com.lucolimac.soccernews.domain.News
import retrofit2.Call

interface NewsRepository {
    fun getNews(): Call<List<News>>
}