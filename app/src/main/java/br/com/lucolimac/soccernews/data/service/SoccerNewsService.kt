package br.com.lucolimac.soccernews.data.service

import br.com.lucolimac.soccernews.domain.News
import retrofit2.http.GET

interface SoccerNewsService {
    @GET("news.json")
    suspend fun getNews(): List<News>
}