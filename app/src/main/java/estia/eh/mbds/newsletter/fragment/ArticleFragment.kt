package estia.eh.mbds.newsletter.fragment

import android.content.Context
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Article
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
    private var mArticle: Article = article

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


        return view
    }

}