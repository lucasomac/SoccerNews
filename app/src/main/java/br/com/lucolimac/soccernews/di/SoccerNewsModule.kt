package br.com.lucolimac.soccernews.di

import br.com.lucolimac.soccernews.data.repository.NewsRepositoryImpl
import br.com.lucolimac.soccernews.data.service.Client
import br.com.lucolimac.soccernews.data.service.SoccerNewsService
import br.com.lucolimac.soccernews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        return Client.getService()
    }
}