package com.example.laboratoire02

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laboratoire02.models.Article


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {
    private var articles: List<Article> = emptyList()

    // ViewHolder pour un article
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCoverImage: ImageView = itemView.findViewById(R.id.ivCoverImage)
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)

    }

    // Créer un viewholder pour chaque élément de la liste
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ArticleViewHolder(view)
    }

    // Retourner le nombre d'articles
    override fun getItemCount(): Int = articles.size

    // Lier les données (titre, description, image) à chaque élément de la liste
    override fun onBindViewHolder(holderArticle: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holderArticle.tvTitle.text = article.title
        // Affichage de la description de l'article
        holderArticle.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(holderArticle.ivCoverImage) // Affichage de l'image
        }
    }

    // Mettre à jour la liste des articles et notifier l'adaptateur
    fun setArticles(articles: List<Article>) {
        this.articles = articles
        notifyDataSetChanged()
    }
}
