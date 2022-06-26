package br.com.lucolimac.soccernews.di

import android.content.Context
import br.com.lucolimac.soccernews.data.FavoritesClient
import br.com.lucolimac.soccernews.data.repository.remote.NewsRepositoryImpl
import br.com.lucolimac.soccernews.data.SoccerClient
import br.com.lucolimac.soccernews.data.repository.local.FavoritesRepositoryImpl
import br.com.lucolimac.soccernews.data.service.FavoritesService
import br.com.lucolimac.soccernews.data.service.SoccerNewsService
import br.com.lucolimac.soccernews.domain.repository.local.FavoritesRepository
import br.com.lucolimac.soccernews.domain.repository.remote.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object SoccerNewsModule {
    @Provides
    fun provideNewsRepositoryImpl(soccerNewsService: SoccerNewsService): NewsRepositoryImpl {
        return NewsRepositoryImpl(soccerNewsService)
    }

    @Provides
    fun provideTheMovieDbRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository {
        return newsRepositoryImpl
    }

    @Provides
    fun provideSoccerNewsService(): SoccerNewsService {
        return SoccerClient.getService()
    }

    @Provides
    fun provideFavoritesClient(@ApplicationContext context: Context): FavoritesClient {
        return FavoritesClient.getInstance(context)
    }

    @Provides
    fun provideFavoritesService(database: FavoritesClient): FavoritesService {
        return database.newsService()
    }

    @Provides
    fun provideFavoritesRepositoryImpl(favoritesService: FavoritesService): FavoritesRepositoryImpl {
        return FavoritesRepositoryImpl(favoritesService)
    }

    @Provides
    fun provideFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository {
        return favoritesRepositoryImpl
    }
}