package br.com.lucolimac.soccernews.domain.repository.remote

import br.com.lucolimac.soccernews.domain.News

interface NewsRepository {
    suspend fun getNews(): List<News>
}