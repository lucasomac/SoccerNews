package br.com.lucolimac.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucolimac.soccernews.domain.News

class NewsViewModel : ViewModel() {

    private val _newsData = MutableLiveData<MutableList<News>>().apply {
        value = mutableListOf(
            News(
                "Vasco vence cruzeiro",
                "Em jogo duro vasco vence o cruzeiro e diminui a vantagem do time mineiro "
            ),
            News(
                "Flamengo perde mais uma",
                "Flamengo perde mais um e se afunda ainda na crise interna"
            )
        )
    }
    val newsData: LiveData<MutableList<News>> = _newsData
}