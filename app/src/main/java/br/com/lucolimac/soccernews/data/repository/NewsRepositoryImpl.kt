package br.com.lucolimac.soccernews.data.repository

import br.com.lucolimac.soccernews.data.service.SoccerNewsService
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.NewsRepository
import retrofit2.Call
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val service: SoccerNewsService) :
    NewsRepository {
    override fun getNews(): Call<List<News>> {
        return service.getNews()
    }
}