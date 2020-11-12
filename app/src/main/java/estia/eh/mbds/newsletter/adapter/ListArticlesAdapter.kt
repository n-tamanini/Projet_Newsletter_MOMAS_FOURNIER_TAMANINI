package estia.eh.mbds.newsletter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Article

class ListArticlesAdapter (
    items: List<Article>
    ) : RecyclerView.Adapter<ListArticlesAdapter.ViewHolder>() {
    private val mNeighbours: List<Article> = items
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ViewHolder(view)
    }

    // Appelée à chaque fois qu'on bind un élément
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val neighbour: Article = mArticles[position]
        // Display Neighbour Name
        holder.mArticleTitle.text = Article.title

        val context: Context = holder.mNeighbourAvatar.context
        // Display Neighbour Avatar
        Glide.with(context)
            .load(neighbour.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_baseline_person_outline_24)
            .error(R.drawable.ic_baseline_person_outline_24)
            .skipMemoryCache(false)
            .into(holder.mNeighbourAvatar)
    }
}