package br.com.lucolimac.soccernews.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.lucolimac.soccernews.data.service.FavoritesService
import br.com.lucolimac.soccernews.domain.News

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class FavoritesClient : RoomDatabase() {
    abstract fun newsService(): FavoritesService

    companion object {
        private var instance: FavoritesClient? = null

        @JvmStatic
        fun getInstance(context: Context): FavoritesClient {
            return instance ?: build(context)
        }

        private fun build(context: Context): FavoritesClient {
            val database =
                Room.databaseBuilder(
                    context,
                    FavoritesClient::class.java,
                    "soccer_news_favorites.db"
                )
                    .allowMainThreadQueries().build()
            instance = database
            return database
        }
    }
}
