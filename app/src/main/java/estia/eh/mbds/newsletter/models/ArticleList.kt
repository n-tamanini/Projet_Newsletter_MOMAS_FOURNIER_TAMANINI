package estia.eh.mbds.newsletter.models

import com.google.gson.annotations.SerializedName

data class ArticleList (
    @SerializedName("articles")
    val articles: List<Article>
)