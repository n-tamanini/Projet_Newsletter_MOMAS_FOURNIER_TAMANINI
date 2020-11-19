package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.FavoriteArticle

interface DeleteFavoriteArticleService {

    fun onDeleteFavoriteButtonClick(favoriteArticle: FavoriteArticle)
}