package com.example.laboratoire02.network

import com.example.laboratoire02.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "f0a69c56aa5d47dba87ba883c47c88ea"

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<NewsResponse>

    // Ajoutez cette méthode pour récupérer toutes les nouvelles

    @GET("v2/everything")
    suspend fun getAllNews(
        @Query("q") query: String, // Le mot-clé à rechercher
        @Query("apiKey") apiKey: String = API_KEY // Votre clé API
    ): Response<NewsResponse>


}
