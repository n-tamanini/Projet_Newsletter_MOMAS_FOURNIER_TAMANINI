package estia.eh.mbds.newsletter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.data.repository.ArticleRepository
import estia.eh.mbds.newsletter.data.service.DeleteFavoriteArticleByTitleService
import estia.eh.mbds.newsletter.models.FavoriteArticle
import estia.eh.mbds.newsletter.data.service.InsertFavoriteArticleService
import estia.eh.mbds.newsletter.models.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ListArticlesAdapter(
        items: List<Article>,
        insertFavoriteArticleService: InsertFavoriteArticleService,
        deleteFavoriteArticleByTitleService: DeleteFavoriteArticleByTitleService,
        listFavoriteArticlesTitle: MutableList<String>,
        private val listenerArticle: (Article) -> Unit
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {

    private val mArticles: List<Article> = items

    private val mInsertFavoriteArticleService = insertFavoriteArticleService
    private val mDeleteFavoriteArticleByTitleService = deleteFavoriteArticleByTitleService
    private var mListFavoriteArticlesTitle = listFavoriteArticlesTitle

    private val DATE_FORMAT_ISO: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val isoFormat = SimpleDateFormat(DATE_FORMAT_ISO)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles[position]

        holder.mArticleTitle.text = article.title
        holder.mArticleDescription.text = article.description
        holder.mArticleAuthor.text = article.author

        val isoDate: Date = isoFormat.parse(article.publishedAt)
		holder.mArticlePublishedAt.text = DateFormat.getDateInstance(DateFormat.LONG).format(isoDate)

        val context : Context = holder.mArticleUrlToImage.context
		
        Glide.with(context) //follow lifecycle
                .load(article.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .placeholder(R.drawable.ic_baseline_filter_hdr_24)
                .error(R.drawable.ic_baseline_filter_hdr_24)
                .skipMemoryCache(false)
                .into(holder.mArticleUrlToImage)


		/*Gestion des favoris 
          Pour déterminer si les articles retournés par l'API ont été mis en favoris précédemment,
          on récupère une liste contenant le titre de tous les articles mis en favoris depuis la base de données
          puis on compare le titre des articles provenant de l'api avec le contenu de la liste*/

        if (mListFavoriteArticlesTitle.contains(article.title)) {
            ArticleRepository.getInstance().updateFavoriteStatus(article, true)
            holder.mFavoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_filled_24)
        } else {
            ArticleRepository.getInstance().updateFavoriteStatus(article, false)
            holder.mFavoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_empty_24)
        }

        holder.mFavoriteButton.setOnClickListener {
            if (!article.isFavorite) {
                ArticleRepository.getInstance().updateFavoriteStatus(article, true)
                holder.mFavoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_filled_24)
                mListFavoriteArticlesTitle.add(article.title)
                insertArticleToFavorites(article)
                Toast.makeText(context, R.string.added_to_favorites, Toast.LENGTH_SHORT).show()

            } else {
                ArticleRepository.getInstance().updateFavoriteStatus(article, false)
                holder.mFavoriteButton.setBackgroundResource(R.drawable.ic_baseline_favorite_empty_24)

                // Pour supprimer un article de la base de données des favoris depuis la vue "Liste des articles",
                // on cherche l'article ayant le même titre que celui qui est affiché et on le supprime de la base de données
                mDeleteFavoriteArticleByTitleService.deleteFavoriteArticleByTitle(article.title)

                for (i in 0 until mListFavoriteArticlesTitle.size) {
                    if (mListFavoriteArticlesTitle[i] == article.title) {
                        mListFavoriteArticlesTitle.removeAt(i)
                    }
                }
                Toast.makeText(context, R.string.removed_from_favorites, Toast.LENGTH_SHORT).show()
            }
        }
        //Gestion du clique sur un article
        holder.itemView.setOnClickListener {
            listenerArticle(article)
        }

    }

    override fun getItemCount(): Int {
        //Returns the total number of items in the data set held by the adapter.
        return mArticles.size
    }

    private fun insertArticleToFavorites(article: Article) {

        val sourceId = article.source?.id
        val sourceName = article.source?.name
        val author = article.author
        val title = article.title
        val description = article.description
        val url = article.url
        val urlToImage = article.urlToImage
        val publishedAt = article.publishedAt
        val content = article.content

        val favoriteArticle = FavoriteArticle(
                articleId = 0,
                sourceId = sourceId,
                sourceName = sourceName,
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content
        )
        mInsertFavoriteArticleService.onFavoriteButtonClick(favoriteArticle)
    }


    class ViewHolder(view: View) :
    //décrit la vue et les métadata des items dans le recycleView
            RecyclerView.ViewHolder(view) {
        val mArticleUrlToImage: ImageView
        val mArticleAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDescription: TextView
        val mArticlePublishedAt: TextView
        val mFavoriteButton: Button

        init {
            // Enable click on item
            mArticleUrlToImage = view.findViewById(R.id.item_list_urlToImage)
            mArticleAuthor = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_title)
            mArticleDescription = view.findViewById(R.id.item_list_description)
            mArticlePublishedAt = view.findViewById(R.id.item_list_publishedAt)
            mFavoriteButton = view.findViewById(R.id.item_list_favorite_button)
        }
    }

}