package com.example.laboratoire02

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratoire02.databinding.FragmentAllNewsBinding
import com.example.laboratoire02.models.Article
import com.example.laboratoire02.models.NewsResponse

import com.example.laboratoire02.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class AllNewsFragment : Fragment(R.layout.fragment_all_news) {

    private var _binding: FragmentAllNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialisation du RecyclerView et de l'adaptateur
        newsAdapter = NewsAdapter()

        // Utiliser l'ID correct du RecyclerView : recyclerViewAllNews
        binding.recyclerViewAllNews.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewAllNews.adapter = newsAdapter

        // Récupérer les nouvelles
        fetchAllNews()
    }

    private fun fetchAllNews() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: Response<NewsResponse> = NewsRepository().getAllNews("us")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.articles?.let {
                            // Mettre à jour l'adaptateur avec les articles récupérés
                            newsAdapter.setArticles(it)
                        }
                    } else {
                        Log.e("AllNewsFragment", "Erreur API: ${response.code()} - ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                Log.e("AllNewsFragment", "Erreur de réseau: ${e.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
