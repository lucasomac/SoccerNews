package br.com.lucolimac.soccernews.domain.repository.local

import br.com.lucolimac.soccernews.domain.News

interface FavoritesRepository {

    suspend fun insertFavorite(news: News): List<News>
    suspend fun selectAllFavorites(): List<News>
    suspend fun deleteFavorite(news: News): List<News>
}