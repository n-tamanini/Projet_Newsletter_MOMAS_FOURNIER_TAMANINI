package estia.eh.mbds.newsletter.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(FavoriteArticle::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteArticleDAO(): FavoriteArticleDAO

}