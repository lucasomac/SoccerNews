package br.com.lucolimac.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lucolimac.soccernews.databinding.FragmentNewsBinding
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.ui.adapter.NewsAdapter
import br.com.lucolimac.soccernews.ui.state.NewsState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val newsViewModel: NewsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onStart() {
        super.onStart()
        newsViewModel.getNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsViewModel.newsState.observe(viewLifecycleOwner) {
            when (it) {
                is NewsState.FavoriteAddSuccess -> setupMessageSuccessAddFavorite()
                is NewsState.FavoriteRemoveSuccess -> setupMessageSuccessRemoveFavorite()
                NewsState.FavoritesFailure -> setupMessageFailureFavorites()
                is NewsState.Loading -> setupProgress(it.isLoading)
                NewsState.NewsFailure -> setupMessageFailureNews()
                is NewsState.NewsSuccess -> setupRecyclerData(it.data)
            }

        }
    }

    private fun setupMessageSuccessAddFavorite() {
        Toast.makeText(context, "Favorito adicionado com sucesso!", Toast.LENGTH_LONG).show()
    }

    private fun setupMessageSuccessRemoveFavorite() {
        Toast.makeText(context, "Favorito removido com sucesso!", Toast.LENGTH_LONG).show()
    }

    private fun setupMessageFailureNews() {
        Toast.makeText(
            context,
            "Não foi possível recuperar a lsita de notícias!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupMessageFailureFavorites() {
        Toast.makeText(
            context,
            "Não foi possível execcutar a operação. Tente Novamente!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupProgress(isLoading: Boolean) {
        if (isLoading)
            binding.progressContent.show()
        else
            binding.progressContent.hide()
    }

    private fun setupRecyclerData(data: List<News>) {
        binding.recyclerNews.adapter = NewsAdapter(data, this::onFavoriteListener)
    }

    private fun onFavoriteListener(news: News) {
        if (news.favorite)
            newsViewModel.removeFavorite(news)
        else
            newsViewModel.createFavorite(news)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}