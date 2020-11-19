package estia.eh.mbds.newsletter.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteArticleDAO {
    @Query("SELECT * FROM favoriteArticle")
    fun getAll(): LiveData<List<FavoriteArticle>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteArticle: FavoriteArticle)

    @Delete
    fun delete(favoriteArticle: FavoriteArticle)
}