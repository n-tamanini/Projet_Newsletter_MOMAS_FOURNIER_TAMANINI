package estia.eh.mbds.newsletter.data.repository

import androidx.lifecycle.LiveData
import estia.eh.mbds.newsletter.models.FavoriteArticle
import estia.eh.mbds.newsletter.data.database.FavoriteArticleDAO

class FavoriteArticleRepository (private val favoriteArticleDAO: FavoriteArticleDAO){

    fun getFavoriteArticles(): LiveData<List<FavoriteArticle>> = favoriteArticleDAO.getAll()

    suspend fun insertArticleIntoFavorites(favoriteArticle: FavoriteArticle) = favoriteArticleDAO.insert(favoriteArticle)

    suspend fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle) = favoriteArticleDAO.delete(favoriteArticle)

}