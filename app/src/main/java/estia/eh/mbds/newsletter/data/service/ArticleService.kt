package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.Article

interface ArticleService {
        /**
         * Get all articles
         * @return [List]
         */
        fun getArticles(): List<Article>

        /**
         * Update "Favorite status" of an existing Article"
         * @param article: Article
         */
        //fun updateFavoriteStatus(article: Article)

    }
