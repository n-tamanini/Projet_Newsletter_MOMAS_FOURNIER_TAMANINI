package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.Article

interface ArticleService {
        /**
         * Get all my Neighbors
         * @return [List]
         */
        val articles: List<Article>

        /**
         * Update "Favorite status" of an existing Neighbour"
         * @param neighbor: Neighbor
         */
        fun updateFavoriteStatus(neighbor: Article)


    }