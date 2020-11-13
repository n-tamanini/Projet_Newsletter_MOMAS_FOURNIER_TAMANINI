package estia.eh.mbds.newsletter.data.database

import androidx.room.*
import estia.eh.mbds.newsletter.models.Article

@Dao
interface FavoriteArticleDAO {
    @Query("SELECT * FROM FavoriteArticle")
    fun getAll(): List<FavoriteArticle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoriteArticle: FavoriteArticle)

    @Delete
    fun delete(favoriteArticle: FavoriteArticle)
}