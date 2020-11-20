package estia.eh.mbds.newsletter.models

data class Article(
        val source: Source?,
        val author: String?,
        val title: String,
        val description: String?,
        val url: String?,
        val urlToImage: String?,
        val publishedAt: String?,
        val content: String?,
        var isFavorite: Boolean
)