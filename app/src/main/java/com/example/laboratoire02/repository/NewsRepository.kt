package com.example.laboratoire02.repository

import com.example.laboratoire02.network.RetrofitInstance
import com.example.laboratoire02.models.NewsResponse
import retrofit2.Response
import android.util.Log
import okhttp3.ResponseBody

class NewsRepository {

    suspend fun getBreakingNews(countryCode: String): Response<NewsResponse> {
        try {
            val response: Response<NewsResponse> = RetrofitInstance.retrofitService.getBreakingNews(countryCode)

            if (response.isSuccessful) {
                val newsResponse = response.body()
                if (newsResponse != null) {
                    Log.d("NewsRepository", "Données reçues : ${newsResponse.articles.size} articles")
                    return response
                } else {
                    Log.e("NewsRepository", "Aucune donnée dans la réponse.")
                    val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
                    return Response.error(404, emptyResponseBody)
                }
            } else {
                Log.e("NewsRepository", "Erreur API: Code ${response.code()}, Message: ${response.message()}")
                Log.e("NewsRepository", "Body de l'erreur : ${response.errorBody()?.string()}")
                val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
                return Response.error(response.code(), emptyResponseBody)
            }
        } catch (e: Exception) {
            Log.e("NewsRepository", "Erreur lors de l'appel API: ${e.localizedMessage}")
            val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
            return Response.error(500, emptyResponseBody)
        }
    }

    // Ajout de la méthode getAllNews
    suspend fun getAllNews(query: String): Response<NewsResponse> {
        try {
            // Effectuer la requête avec le mot-clé
            val response: Response<NewsResponse> = RetrofitInstance.retrofitService.getAllNews(query)

            if (response.isSuccessful) {
                val newsResponse = response.body()
                if (newsResponse != null) {
                    Log.d("NewsRepository", "Données reçues : ${newsResponse.articles.size} articles")
                    return response
                } else {
                    Log.e("NewsRepository", "Aucune donnée dans la réponse.")
                    val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
                    return Response.error(404, emptyResponseBody)
                }
            } else {
                Log.e("NewsRepository", "Erreur API: Code ${response.code()}, Message: ${response.message()}")
                Log.e("NewsRepository", "Body de l'erreur : ${response.errorBody()?.string()}")
                val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
                return Response.error(response.code(), emptyResponseBody)
            }
        } catch (e: Exception) {
            Log.e("NewsRepository", "Erreur lors de l'appel API: ${e.localizedMessage}")
            val emptyResponseBody: ResponseBody = ResponseBody.create(null, "")
            return Response.error(500, emptyResponseBody)
        }
    }
}
