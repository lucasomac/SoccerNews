package br.com.lucolimac.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.lucolimac.soccernews.databinding.FragmentNewsBinding
import br.com.lucolimac.soccernews.ui.adapter.NewsAdapter

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val newsViewModel: NewsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        newsViewModel.newsData.observe(viewLifecycleOwner) {
            binding.recyclerNews.adapter = NewsAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}