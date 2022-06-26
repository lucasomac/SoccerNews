package br.com.lucolimac.soccernews.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.local.FavoritesRepository
import br.com.lucolimac.soccernews.ui.state.FavoritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) : ViewModel() {
    private var _favoritesState: MutableLiveData<FavoritesState> =
        MutableLiveData(FavoritesState.Loading(false))
    val favoritesState: MutableLiveData<FavoritesState>
        get() = _favoritesState


    fun getFavorites() {
        viewModelScope.launch {
            _favoritesState.postValue(FavoritesState.Loading(true))
            try {
                favoritesRepository.selectAllFavorites().run {
                    _favoritesState.postValue(FavoritesState.FavoritesSuccess(this))
                }
                _favoritesState.postValue(FavoritesState.Loading(false))
            } catch (throws: Throwable) {
                _favoritesState.postValue(FavoritesState.FavoritesFailure)
                _favoritesState.postValue(FavoritesState.Loading(false))
            } finally {
                _favoritesState.postValue(FavoritesState.Loading(false))
            }
        }
    }

    fun removeFavorite(news: News) {
        viewModelScope.launch {
            _favoritesState.postValue(FavoritesState.Loading(true))
            try {
                _favoritesState.postValue(
                    FavoritesState.FavoritesRemoveSuccess(
                        favoritesRepository.deleteFavorite(
                            news
                        )
                    )
                )
                _favoritesState.postValue(FavoritesState.Loading(false))
            } catch (throws: Throwable) {
                _favoritesState.postValue(FavoritesState.FavoritesFailure)
                _favoritesState.postValue(FavoritesState.Loading(false))
            } finally {
                _favoritesState.postValue(FavoritesState.Loading(false))
            }
        }
    }
}