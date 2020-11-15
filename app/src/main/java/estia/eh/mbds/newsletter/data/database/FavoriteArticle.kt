package estia.eh.mbds.newsletter.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteArticle(
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val articleId: Int,
        @ColumnInfo(name = "sourceId") val sourceId: String?,
        @ColumnInfo(name = "sourceName") val sourceName: String?,
        @ColumnInfo(name = "author") val author: String?,
        @ColumnInfo(name = "title") val title: String?,
        @ColumnInfo(name = "description") val description: String?,
        @ColumnInfo(name = "url") val url: String?,
        @ColumnInfo(name = "urlToImage") val urlToImage: String?,
        @ColumnInfo(name = "publishedAt") val publishedAt: String?,
        @ColumnInfo(name = "content") val content: String?
)