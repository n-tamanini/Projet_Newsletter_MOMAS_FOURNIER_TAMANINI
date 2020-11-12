package estia.eh.mbds.newsletter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Article

class ListArticlesAdapter (
        items: List<Article>
) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {
    private val mArticles: List<Article> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: Article = mArticles[position]

        holder.mArticleTitle.text = article.title
        holder.mArticleDescription.text = article.description

        val context : Context = holder.mArticleUrlToImage.context

        Glide.with(context)
                .load(article.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .placeholder(R.drawable.ic_baseline_filter_hdr_24)
                .error(R.drawable.ic_baseline_filter_hdr_24)
                .skipMemoryCache(false)
                .into(holder.mArticleUrlToImage)
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
        val mArticleDate: TextView

        init {
            // Enable click on item
            mArticleUrlToImage = view.findViewById(R.id.item_list_urlToImage)
            mArticleAuthor = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_title)
            mArticleDescription = view.findViewById(R.id.item_list_description)
            mArticleDate = view.findViewById(R.id.item_list_publishedAt)

        }
    }
}