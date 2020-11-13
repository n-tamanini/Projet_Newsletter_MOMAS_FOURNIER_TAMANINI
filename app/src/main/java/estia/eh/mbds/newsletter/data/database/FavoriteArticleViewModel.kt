package estia.eh.mbds.newsletter.data.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import estia.eh.mbds.newsletter.data.FavoriteArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteArticleViewModel(application: Application): AndroidViewModel(application) {

    private val getAll: LiveData<List<FavoriteArticle>>
    private val repository: FavoriteArticleRepository

    init{
        val favoriteArticleDao = AppDatabase.getDatabase(application).favoriteArticleDAO()
        repository = FavoriteArticleRepository(favoriteArticleDao)
        getAll = repository.getFavoriteArticles()
    }

    fun insert(favoriteArticle: FavoriteArticle){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertArticleIntoFavorites(favoriteArticle)
        }
    }

}