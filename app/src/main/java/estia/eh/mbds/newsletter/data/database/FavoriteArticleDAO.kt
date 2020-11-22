package estia.eh.mbds.newsletter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import estia.eh.mbds.newsletter.models.FavoriteArticle

// L'architecture de la base de données Room provient de cette
// série de tutos : https://www.youtube.com/playlist?list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o
// (Vidéos #1 à #5)

@Dao
interface FavoriteArticleDAO {
    @Query("SELECT * FROM favoriteArticle ORDER BY id DESC")
    fun getAll(): LiveData<List<FavoriteArticle>>

    @Query("SELECT title FROM favoriteArticle")
    fun getAllFavoriteArticlesTitle(): LiveData<MutableList<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteArticle: FavoriteArticle)

    @Delete
    suspend fun delete(favoriteArticle: FavoriteArticle)

    @Query("DELETE FROM favoriteArticle WHERE title = :articleTitle")
    suspend fun deleteFavoriteByArticleTitle(articleTitle: String)
}