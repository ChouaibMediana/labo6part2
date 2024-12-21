package com.example.laboratoire02.viewModels


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.laboratoire02.models.NewsResponse
import com.example.laboratoire02.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val breakingNews: MutableLiveData<NewsResponse?> = MutableLiveData()

    init {
        getBreakingNews("us")
    }

    private fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        try {
            // Récupérer la réponse du repository
            val response: Response<NewsResponse> = newsRepository.getBreakingNews(countryCode)

            // Vérifier si la réponse est réussie
            if (response.isSuccessful) {
                // Si la réponse est valide, nous postons la réponse
                response.body()?.let {
                    breakingNews.postValue(it)
                } ?: run {
                    // Si body est null, on affiche un message d'erreur
                    Log.e("NewsViewModel", "Réponse vide, body est null")
                    breakingNews.postValue(null)
                }
            } else {
                // Si la réponse échoue, on log l'erreur
                Log.e("NewsViewModel", "Échec de la requête: ${response.code()}")
                breakingNews.postValue(null)
            }
        } catch (e: Exception) {
            // Loguer l'exception
            Log.e("NewsViewModel", "Erreur lors de l'appel API: ${e.localizedMessage}")
            breakingNews.postValue(null)
        }
    }
}
