package br.com.lucolimac.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.lucolimac.soccernews.databinding.FragmentFavoritesBinding
import br.com.lucolimac.soccernews.domain.News
import br.com.lucolimac.soccernews.ui.adapter.NewsAdapter
import br.com.lucolimac.soccernews.ui.state.FavoritesState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val favoritesViewModel: FavoritesViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onResume() {
        super.onResume()
        favoritesViewModel.getFavorites()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritesViewModel.favoritesState.observe(viewLifecycleOwner) {
            when (it) {
                FavoritesState.FavoritesFailure -> setupMessageFailureFavorites()
                is FavoritesState.FavoritesRemoveSuccess -> setupMessageSuccessRemoveFavorite()
                is FavoritesState.FavoritesSuccess -> setupScreenUi(it.data)
                is FavoritesState.Loading -> setupProgress(it.isLoading)
            }
        }
    }

    private fun setupScreenUi(data: List<News>) {
        if (data.isEmpty()) {
            binding.recyclerFavorites.visibility = View.GONE
            binding.textViewNoFavorites.visibility = View.VISIBLE
        } else {
            setupRecyclerData(data)
        }
    }

    private fun setupMessageFailureFavorites() {
        Toast.makeText(
            context, "Não foi possível execcutar a operação. Tente Novamente!", Toast.LENGTH_SHORT
        ).show()
    }

    private fun setupRecyclerData(data: List<News>) {
        binding.recyclerFavorites.adapter = NewsAdapter(data, this::onFavoriteListener)
    }

    private fun setupMessageSuccessRemoveFavorite() {
        Toast.makeText(context, "Favorito removido com sucesso!", Toast.LENGTH_LONG).show()
    }

    private fun setupProgress(isLoading: Boolean) {
        if (isLoading) binding.progressContent.show()
        else binding.progressContent.hide()
    }

    private fun onFavoriteListener(news: News) {
        favoritesViewModel.removeFavorite(news)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}