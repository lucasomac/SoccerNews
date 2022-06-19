package br.com.lucolimac.soccernews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.domain.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    private var _newsData = MutableLiveData<List<News>>().apply { value = listOf() }
    val newsData: LiveData<List<News>> = _newsData

    fun getNews() {
        newsRepository.getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    _newsData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {

            }
        })
    }
}