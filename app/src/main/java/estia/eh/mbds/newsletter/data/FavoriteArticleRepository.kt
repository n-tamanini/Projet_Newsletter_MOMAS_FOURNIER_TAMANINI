package estia.eh.mbds.newsletter.data

import androidx.lifecycle.LiveData
import estia.eh.mbds.newsletter.data.database.FavoriteArticle
import estia.eh.mbds.newsletter.data.database.FavoriteArticleDAO

class FavoriteArticleRepository (private val favoriteArticleDAO: FavoriteArticleDAO){

    fun getFavoriteArticles(): LiveData<List<FavoriteArticle>> = favoriteArticleDAO.getAll()

    suspend fun insertArticleIntoFavorites(favoriteArticle: FavoriteArticle) = favoriteArticleDAO.insert(favoriteArticle)

    fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle) = favoriteArticleDAO.delete(favoriteArticle)

}