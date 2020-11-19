package estia.eh.mbds.newsletter.data.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import estia.eh.mbds.newsletter.data.repository.FavoriteArticleRepository
import estia.eh.mbds.newsletter.models.FavoriteArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteArticleViewModel(application: Application) : AndroidViewModel(application) {

    val getAllFavoriteArticles: LiveData<List<FavoriteArticle>>
    val getAllFavoriteArticlesTitle: LiveData<MutableList<String>>
    private val repository: FavoriteArticleRepository

    init {
        val favoriteArticleDao = AppDatabase.getDatabase(application).favoriteArticleDAO()
        repository = FavoriteArticleRepository(favoriteArticleDao)
        getAllFavoriteArticles = repository.getFavoriteArticles()
        getAllFavoriteArticlesTitle = repository.getAllFavoriteArticlesTitle()
    }

    fun insert(favoriteArticle: FavoriteArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertArticleIntoFavorites(favoriteArticle)
        }
    }

    fun deleteFavoriteArticle(favoriteArticle: FavoriteArticle) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteArticle(favoriteArticle)
        }
    }

    fun deleteFavoriteByArticleTitle(articleTitle: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteFavoriteByArticleTitle(articleTitle)
        }
    }

}