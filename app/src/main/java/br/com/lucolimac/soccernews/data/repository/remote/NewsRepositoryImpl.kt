package br.com.lucolimac.soccernews.data.repository.remote

import br.com.lucolimac.soccernews.data.service.SoccerNewsService
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.remote.NewsRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(private val service: SoccerNewsService) :
    NewsRepository {
    override suspend fun getNews(): List<News> {
        return withContext(IO) {
            service.getNews()
        }
    }
}