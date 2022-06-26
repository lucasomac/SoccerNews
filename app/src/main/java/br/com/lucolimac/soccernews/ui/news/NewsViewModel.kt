package br.com.lucolimac.soccernews.ui.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.local.FavoritesRepository
import br.com.lucolimac.soccernews.domain.repository.remote.NewsRepository
import br.com.lucolimac.soccernews.ui.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    //    private var _newsData = MutableLiveData<List<News>>().apply { value = listOf() }
//    val newsData: LiveData<List<News>> = _newsData
    private var _newsState: MutableLiveData<NewsState> = MutableLiveData(NewsState.Loading(false))
    val newsState: MutableLiveData<NewsState>
        get() = _newsState

    fun getNews() {
        viewModelScope.launch {
            _newsState.postValue(NewsState.Loading(true))
            try {
                _newsState.postValue(NewsState.Loading(false))
                val list = newsRepository.getNews()
                _newsState.postValue(NewsState.NewsSuccess(list))
            } catch (throws: Throwable) {
                _newsState.postValue(NewsState.Loading(false))
                _newsState.postValue(NewsState.NewsFailure)
            } finally {
//                _newsState.postValue(NewsState.Loading(false))
            }
        }
    }

    fun createFavorite(news: News) {
        viewModelScope.launch {
            _newsState.postValue(NewsState.Loading(true))
            try {
                _newsState.postValue(
                    NewsState.FavoriteAddSuccess(
                        favoritesRepository.insertFavorite(
                            news
                        )
                    )
                )
                _newsState.postValue(NewsState.Loading(false))
            } catch (throws: Throwable) {
                _newsState.postValue(NewsState.FavoritesFailure)
                _newsState.postValue(NewsState.Loading(false))
            } finally {
                _newsState.postValue(NewsState.Loading(false))
            }
        }
    }

    fun removeFavorite(news: News) {
        viewModelScope.launch {
            _newsState.postValue(NewsState.Loading(true))
            try {
                _newsState.postValue(
                    NewsState.FavoriteRemoveSuccess(
                        favoritesRepository.deleteFavorite(
                            news
                        )
                    )
                )
                _newsState.postValue(NewsState.Loading(false))
            } catch (throws: Throwable) {
                _newsState.postValue(NewsState.FavoritesFailure)
                _newsState.postValue(NewsState.Loading(false))
            } finally {
                _newsState.postValue(NewsState.Loading(false))
            }
        }
    }
}