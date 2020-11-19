package estia.eh.mbds.newsletter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import estia.eh.mbds.newsletter.models.FavoriteArticle

@Dao
interface FavoriteArticleDAO {
    @Query("SELECT * FROM favoriteArticle ORDER BY id DESC")
    fun getAll(): LiveData<List<FavoriteArticle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteArticle: FavoriteArticle)

    @Delete
    suspend fun delete(favoriteArticle: FavoriteArticle)
}