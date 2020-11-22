package estia.eh.mbds.newsletter.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.data.database.FavoriteArticleViewModel
import estia.eh.mbds.newsletter.data.repository.ArticleRepository
import estia.eh.mbds.newsletter.models.Article
import estia.eh.mbds.newsletter.models.FavoriteArticle
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ArticleFragment(article: Article) : Fragment() {

    private lateinit var mArticleUrlToImage: ImageView
    private lateinit var mArticleAuthor: TextView
    private lateinit var mArticleTitle: TextView
    private lateinit var mArticleDescription: TextView
    private lateinit var mArticlePublishedAt: TextView
    private lateinit var mArticleLink: TextView
    private lateinit var mFavoriteButton: FloatingActionButton
    private var mArticle: Article = article
    private lateinit var mFavoriteArticleViewModel: FavoriteArticleViewModel

    private val DATE_FORMATISO: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val isoFormat = SimpleDateFormat(DATE_FORMATISO)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.article_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.article_name)
        }

        mArticleUrlToImage = view.findViewById(R.id.item_list_urlToImage)
        mArticleAuthor = view.findViewById(R.id.item_list_author)
        mArticleTitle = view.findViewById(R.id.item_list_title)
        mArticleDescription = view.findViewById(R.id.item_list_description)
        mArticlePublishedAt = view.findViewById(R.id.item_list_publishedAt)
        mArticleLink = view.findViewById(R.id.item_link_article)
        mFavoriteButton = view.findViewById(R.id.article_fav_btn)

        mFavoriteArticleViewModel = ViewModelProvider(this).get(FavoriteArticleViewModel::class.java)

        mArticleTitle.text = mArticle.title
        mArticleDescription.text = mArticle.description
        mArticleAuthor.text = mArticle.author
        mArticleLink.text = mArticle.url

        mArticleLink.setMovementMethod(LinkMovementMethod.getInstance())

        val isoDate: Date = isoFormat.parse(mArticle.publishedAt)
        val date = DateFormat.getDateInstance(DateFormat.LONG).format(isoDate)
        mArticlePublishedAt.text = date

        val context: Context = mArticleUrlToImage.context
        Glide.with(context) //follow lifecycle
                .load(mArticle.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .placeholder(R.drawable.ic_baseline_filter_hdr_24)
                .error(R.drawable.ic_baseline_filter_hdr_24)
                .skipMemoryCache(false)
                .into(mArticleUrlToImage)

        // Gestion modification du bouton après ajout
        if (mArticle.isFavorite) {
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_filled_24)
        } else {
            mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_empty_24)
        }

        mFavoriteButton.setOnClickListener {
            if (!mArticle.isFavorite) {
                ArticleRepository.getInstance().updateFavoriteStatus(mArticle, true)
                mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_filled_24)
                insertArticleToFavorites(mArticle)
            } else {
                ArticleRepository.getInstance().updateFavoriteStatus(mArticle, false)
                mFavoriteButton.setImageResource(R.drawable.ic_baseline_favorite_empty_24)
                mFavoriteArticleViewModel.deleteFavoriteByArticleTitle(mArticle.title)
            }
        }

        return view
    }

    // Ajout des articles à la liste des favoris
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
        mFavoriteArticleViewModel.insert(favoriteArticle)
    }


















}