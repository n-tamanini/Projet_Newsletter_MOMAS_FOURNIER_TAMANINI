package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.adapter.ListArticlesAdapter
import estia.eh.mbds.newsletter.data.repository.ArticleRepository
import estia.eh.mbds.newsletter.models.Article
import estia.eh.mbds.newsletter.models.FavoriteArticle
import estia.eh.mbds.newsletter.data.database.FavoriteArticleViewModel
import estia.eh.mbds.newsletter.data.service.DeleteFavoriteArticleByTitleService
import estia.eh.mbds.newsletter.data.service.InsertFavoriteArticleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListArticlesFragment : Fragment(), InsertFavoriteArticleService, DeleteFavoriteArticleByTitleService {
    private lateinit var recyclerView: RecyclerView
    private lateinit var mFavoriteArticleViewModel: FavoriteArticleViewModel
    private var mListFavoriteArticlesTitle: MutableList<String> = mutableListOf<String>()
    private lateinit var mCountry: String
    private lateinit var mCategory: String

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

        mFavoriteArticleViewModel.getAllFavoriteArticlesTitle.observe(viewLifecycleOwner, Observer {
            mListFavoriteArticlesTitle = it
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val country: String? = arguments?.getString("country")
        val category: String? = arguments?.getString("category")

        if (country != null && category != null){
            mCountry = country
            println("mCountry : $mCountry")
            mCategory = category
            println("mCategory : $mCategory")
        } else {
            // Valeurs par défaut. Normalement, il est impossible que country ou category
            // que l'on récupère dans "arguments" soient nuls
            mCountry = "fr"
            mCategory = "general"
        }

        getArticlesByCountryAndCategory(mCountry, mCategory)
    }


    private fun getArticlesByCountry(country: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticlesByCountry(country)
            bindData(articles)
        }
    }

    private fun getArticlesByCountryAndCategory(country: String, category: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticlesByCountryAndCategory(country,category)
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
            val adapter = ListArticlesAdapter(
                    articles,
                    this@ListArticlesFragment,
                    this@ListArticlesFragment,
                    mListFavoriteArticlesTitle
            ) { article ->
                requireFragmentManager().beginTransaction().apply {
                    replace(R.id.fragment_container, ArticleFragment(article))
                    addToBackStack(null)
                }.commit()
            }

            recyclerView.adapter = adapter
        }
    }

    override fun onFavoriteButtonClick(favoriteArticle: FavoriteArticle) {
        mFavoriteArticleViewModel.insert(favoriteArticle)
    }

    override fun deleteFavoriteArticleByTitle(articleTitle: String) {
        mFavoriteArticleViewModel.deleteFavoriteByArticleTitle(articleTitle)
    }

}