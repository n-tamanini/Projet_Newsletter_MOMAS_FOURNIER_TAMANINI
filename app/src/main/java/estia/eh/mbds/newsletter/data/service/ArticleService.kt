package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.Article

interface ArticleService {

    fun getArticlesByCountry(country: String): List<Article>

    fun getArticlesByCountryAndCategory(country: String, category: String):List<Article>
}
