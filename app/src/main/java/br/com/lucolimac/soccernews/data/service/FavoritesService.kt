package br.com.lucolimac.soccernews.data.service

import androidx.room.*
import br.com.lucolimac.soccernews.domain.News

@Dao
interface FavoritesService {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(news: News)

    @Query("SELECT * FROM news")
    fun readNews(): List<News>

    @Delete
    fun deleteNews(news: News)
}