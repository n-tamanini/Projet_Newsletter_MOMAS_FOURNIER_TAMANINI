package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.Article

interface ArticleService {
    fun getArticles(): List<Article>
}