package br.com.lucolimac.soccernews.data.repository.local

import br.com.lucolimac.soccernews.data.service.FavoritesService
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.local.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val favoritesService: FavoritesService) :
    FavoritesRepository {
    override suspend fun insertFavorite(news: News): List<News> {
        favoritesService.insertNews(news)
        return selectAllFavorites()
    }

    override suspend fun selectAllFavorites(): List<News> {
        return favoritesService.readNews()
    }

    override suspend fun deleteFavorite(news: News): List<News> {
        favoritesService.deleteNews(news)
        return selectAllFavorites()
    }
}