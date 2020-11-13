package estia.eh.mbds.newsletter.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.data.service.ArticleOnlineService
import estia.eh.mbds.newsletter.fragment.ArticleFragment
import estia.eh.mbds.newsletter.models.Article
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ListArticlesAdapter (
        items: List<Article>
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {

    private val mArticles: List<Article> = items

    private val DATE_FORMATISO: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val isoFormat = SimpleDateFormat(DATE_FORMATISO)

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
        val date = DateFormat.getDateInstance(DateFormat.LONG).format(isoDate)
        holder.mArticlePublishedAt.text = date
        holder.mFavoriteButton.setOnClickListener{
            
        }

        val context : Context = holder.mArticleUrlToImage.context
		
        Glide.with(context) //follow lifecycle
                .load(article.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .placeholder(R.drawable.ic_baseline_filter_hdr_24)
                .error(R.drawable.ic_baseline_filter_hdr_24)
                .skipMemoryCache(false)
                .into(holder.mArticleUrlToImage)

        holder.mArticleTitle.setOnClickListener{
            val intent = Intent(context, ArticleFragment)
            intent.putExtra("url", holder.mArticleUrlToImage)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return mArticles.size
    }

    class ViewHolder(view: View) :
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