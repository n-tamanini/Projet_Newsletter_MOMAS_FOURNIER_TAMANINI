package estia.eh.mbds.newsletter.fragment

import android.R.attr.name
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ArticleFragment : Fragment() {

    private lateinit var mArticleUrlToImage: ImageView
    private lateinit var mArticleAuthor: TextView
    private lateinit var mArticleTitle: TextView
    private lateinit var mArticleDescription: TextView
    private lateinit var mArticlePublishedAt: TextView
    private lateinit var mArticleLink: TextView

    private val DATE_FORMATISO: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val isoFormat = SimpleDateFormat(DATE_FORMATISO)

    private lateinit var recyclerView: RecyclerView

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

        return view
    }

    fun setArticle(article : Article){
        mArticleAuthor.text = article.author
        mArticleTitle.text = article.title
        mArticleDescription.text = article.description
        mArticleAuthor.text = article.author
        mArticleLink.text = article.url


        val isoDate: Date = isoFormat.parse(article.publishedAt)
        val date = DateFormat.getDateInstance(DateFormat.LONG).format(isoDate)
        mArticlePublishedAt.text = date
    }
}