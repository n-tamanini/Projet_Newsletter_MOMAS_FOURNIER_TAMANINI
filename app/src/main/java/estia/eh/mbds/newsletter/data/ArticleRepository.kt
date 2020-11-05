package estia.eh.mbds.newsletter.data

import estia.eh.mbds.newsletter.data.service.ArticleOnlineService
import estia.eh.mbds.newsletter.data.service.ArticleService
import estia.eh.mbds.newsletter.models.Article

class ArticleRepository {
    private val articleService: ArticleService

    init{
        articleService = ArticleOnlineService()
    }

    fun getArticles():List<Article> = articleService.getArticles()

    companion object {
        private var instance: ArticleRepository? = null
        fun getInstance(): ArticleRepository {
            if (instance == null) {
                instance = ArticleRepository()
            }
            return instance!!
        }
    }
}