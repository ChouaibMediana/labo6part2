package com.example.laboratoire02.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laboratoire02.repository.NewsRepository
import com.example.laboratoire02.viewModels.NewsViewModel

class NewsViewModelProviderFactory(
    private val newsRepository: NewsRepository
) : ViewModelProvider.Factory {

    // Cette méthode est responsable de la création du ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Vérification que le ViewModel que nous essayons de créer est bien de type NewsViewModel
        return if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") // Pour éviter l'avertissement sur le cast non vérifiable
            NewsViewModel(newsRepository) as T
        } else {
            // Si le ViewModel demandé n'est pas de type NewsViewModel, une exception est levée
            throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
