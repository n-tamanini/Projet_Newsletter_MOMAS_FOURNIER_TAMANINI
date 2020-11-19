package estia.eh.mbds.newsletter.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.data.service.DeleteFavoriteArticleService
import estia.eh.mbds.newsletter.models.FavoriteArticle
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class ListFavoritesAdapter(deleteFavoriteArticleService: DeleteFavoriteArticleService) : RecyclerView.Adapter<ListFavoritesAdapter.ViewHolder>() {

    private val DATE_FORMAT_ISO: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private val isoFormat = SimpleDateFormat(DATE_FORMAT_ISO)

    private var mFavoriteArticles = emptyList<FavoriteArticle>()

    private val mDeleteFavoriteArticleService = deleteFavoriteArticleService

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.favorite_article_item, parent, false))
    }


    override fun getItemCount(): Int {
        return mFavoriteArticles.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favoriteArticle: FavoriteArticle = mFavoriteArticles[position]

        val context: Context = holder.mArticleUrlToImage.context
        Glide.with(context)
                .load(favoriteArticle.urlToImage)
                .apply(RequestOptions.fitCenterTransform())
                .placeholder(R.drawable.ic_baseline_filter_hdr_24)
                .error(R.drawable.ic_baseline_filter_hdr_24)
                .skipMemoryCache(false)
                .into(holder.mArticleUrlToImage)

        holder.mArticleTitle.text = favoriteArticle.title
        holder.mArticleDescription.text = favoriteArticle.description
        holder.mArticleAuthor.text = favoriteArticle.author

        val isoDate: Date = isoFormat.parse(favoriteArticle.publishedAt)
        holder.mArticlePublishedAt.text = DateFormat.getDateInstance(DateFormat.LONG).format(isoDate)

        holder.mDeleteButton.setOnClickListener {
            val alert = AlertDialog.Builder(context)
            // set message of alert dialog
            alert.setMessage(R.string.wantToDeleteFavoriteArticle)
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton(R.string.yes, DialogInterface.OnClickListener { _, _ ->
                        mDeleteFavoriteArticleService.onDeleteFavoriteButtonClick(favoriteArticle)
                    })
                    // negative button text and action
                    .setNegativeButton(R.string.no, DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    })

            val alertDialog: AlertDialog = alert.create()
            alertDialog.show()
        }

    }

    fun setData(favoriteArticles: List<FavoriteArticle>){
        this.mFavoriteArticles = favoriteArticles
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) :
            RecyclerView.ViewHolder(view) {
        val mArticleUrlToImage: ImageView
        val mArticleAuthor: TextView
        val mArticleTitle: TextView
        val mArticleDescription: TextView
        val mArticlePublishedAt: TextView
        val mDeleteButton: Button


        init {
            // Enable click on item
            mArticleUrlToImage = view.findViewById(R.id.item_list_urlToImage)
            mArticleAuthor = view.findViewById(R.id.item_list_author)
            mArticleTitle = view.findViewById(R.id.item_list_title)
            mArticleDescription = view.findViewById(R.id.item_list_description)
            mArticlePublishedAt = view.findViewById(R.id.item_list_publishedAt)
            mDeleteButton = view.findViewById(R.id.item_list_delete_button)
        }

    }


}

