package com.example.laboratoire02


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.laboratoire02.databinding.FragmentBreakingNewsBinding
import com.example.laboratoire02.repository.NewsRepository
import com.example.laboratoire02.viewModels.NewsViewModel
import com.example.laboratoire02.viewModels.NewsViewModelProviderFactory

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news) {

    // Déclaration de la liaison de vue (Binding)
    private var _binding: FragmentBreakingNewsBinding? = null
    private val binding get() = _binding!!

    // Déclaration des variables pour ViewModel et Adapter
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    // Méthode appelée lors de la création de la vue du fragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialisation du Binding
        _binding = FragmentBreakingNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Méthode appelée après la création de la vue
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation du ViewModel avec le Repository
        val newsRepository = NewsRepository()
        val viewModelProviderFactory = NewsViewModelProviderFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        // Initialisation de l'adaptateur pour le RecyclerView
        newsAdapter = NewsAdapter()

        // Configuration du RecyclerView
        binding.rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        // Observer les données du ViewModel et mettre à jour l'adaptateur
        viewModel.breakingNews.observe(viewLifecycleOwner) { newsResponse ->
            newsResponse?.let {
                Log.d("BreakingNews", "Articles: ${it.articles.size}")
                newsAdapter.setArticles(it.articles)
            } ?: run {
                Log.d("BreakingNews", "Aucune donnée reçue")
            }
        }

    }

    // Méthode appelée lors de la destruction de la vue pour nettoyer la mémoire
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libérer la mémoire en évitant les fuites de mémoire
    }
}

