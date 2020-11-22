package estia.eh.mbds.newsletter.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import estia.eh.mbds.newsletter.models.FavoriteArticle

// L'architecture de la base de données Room provient de cette
// série de tutos : https://www.youtube.com/playlist?list=PLSrm9z4zp4mEPOfZNV9O-crOhoMa0G2-o
// (Vidéos #1 à #5)

@Database(entities = [FavoriteArticle::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteArticleDAO(): FavoriteArticleDAO

    // Ici, la fonction getDatabase permet de récupérer une unique instance de la base de données pour toute l'application

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}