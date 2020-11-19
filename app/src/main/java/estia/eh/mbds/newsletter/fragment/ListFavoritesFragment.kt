package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.adapter.ListFavoritesAdapter
import estia.eh.mbds.newsletter.models.FavoriteArticle
import estia.eh.mbds.newsletter.data.database.FavoriteArticleViewModel
import estia.eh.mbds.newsletter.data.service.DeleteFavoriteArticleService

class ListFavoritesFragment: Fragment(), DeleteFavoriteArticleService {

    private lateinit var recyclerView: RecyclerView

    private lateinit var mFavoriteArticleViewModel: FavoriteArticleViewModel

    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_favorite_articles_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.toolbar_name_list_favorite_articles)
        }

        recyclerView = view.findViewById(R.id.articles_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )
        val adapter = ListFavoritesAdapter(this@ListFavoritesFragment)
        recyclerView.adapter = adapter

        mFavoriteArticleViewModel = ViewModelProvider(this).get(FavoriteArticleViewModel::class.java)
        mFavoriteArticleViewModel.getAllFavoriteArticles.observe(viewLifecycleOwner, Observer {favoriteArticle ->
            adapter.setData(favoriteArticle)
        })

        return view
    }

    override fun onDeleteFavoriteButtonClick(favoriteArticle: FavoriteArticle) {
        mFavoriteArticleViewModel.deleteFavoriteArticle(favoriteArticle)
    }

}
