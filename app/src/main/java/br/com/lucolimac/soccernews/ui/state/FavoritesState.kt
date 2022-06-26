package br.com.lucolimac.soccernews.ui.state

import br.com.lucolimac.soccernews.domain.News


sealed class FavoritesState {
    data class FavoritesSuccess(val data: List<News>) : FavoritesState()
    object FavoritesFailure : FavoritesState()
    data class FavoritesRemoveSuccess(val data: List<News>) : FavoritesState()
    data class Loading(val isLoading: Boolean) : FavoritesState()
}
