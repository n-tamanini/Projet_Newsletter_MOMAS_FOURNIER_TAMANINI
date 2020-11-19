package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.adapter.ListArticlesAdapter
import estia.eh.mbds.newsletter.data.ArticleRepository
import estia.eh.mbds.newsletter.models.Article
import estia.eh.mbds.newsletter.data.database.FavoriteArticle
import estia.eh.mbds.newsletter.data.database.FavoriteArticleViewModel
import estia.eh.mbds.newsletter.data.database.OnFavoriteButtonClickListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListArticlesFragment : Fragment(), OnFavoriteButtonClickListener {
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
        val view = inflater.inflate(R.layout.list_articles_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.toolbar_name_list_articles)
        }

        recyclerView = view.findViewById(R.id.articles_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        requireContext(),
                        DividerItemDecoration.VERTICAL
                )
        )

        mFavoriteArticleViewModel = ViewModelProvider(this).get(FavoriteArticleViewModel::class.java)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArticles()
    }

    private fun getArticles() {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticles()
            bindData(articles)
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: List<Article>) {
        lifecycleScope.launch(Dispatchers.Main) {
            val adapter = ListArticlesAdapter(articles, this@ListArticlesFragment)
            recyclerView.adapter = adapter
        }
    }

    override fun onFavoriteButtonClick(favoriteArticle: FavoriteArticle) {
        mFavoriteArticleViewModel.insert(favoriteArticle)
    }

}