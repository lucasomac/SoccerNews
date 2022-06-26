package br.com.lucolimac.soccernews.ui.state

import br.com.lucolimac.soccernews.domain.News

sealed class NewsState {
    data class NewsSuccess(val data: List<News>) : NewsState()
    object NewsFailure : NewsState()
    data class FavoriteAddSuccess(val data: List<News>) : NewsState()
    data class FavoriteRemoveSuccess(val data: List<News>) : NewsState()
//    data class FavoriteListSuccess(val data: List<News>) : NewsState()
    object FavoritesFailure : NewsState()
    data class Loading(val isLoading: Boolean) : NewsState()

}
