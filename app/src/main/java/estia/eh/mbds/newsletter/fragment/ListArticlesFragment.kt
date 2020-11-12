package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.adapter.ListArticlesAdapter
import estia.eh.mbds.newsletter.data.ArticleRepository
import estia.eh.mbds.newsletter.models.Article
import estia.eh.mbds.newsletter.models.NavigationListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListArticlesFragment : Fragment()  {
    private lateinit var recyclerView: RecyclerView


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
            it.updateTitle(R.string.toolbar_name)
        }

        recyclerView = view.findViewById(R.id.articles_list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //val articles = ArticleRepository.getInstance().getArticles()
        //val adapter = ListArticlesAdapter(neighbors)
        //recyclerView.adapter = adapter
    }

    private fun getArticles() {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticles()
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: List<Article>) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
            val adapter = ListArticlesAdapter(articles)
            recyclerView.adapter = adapter
        }
    }



    /** ----------------------------------------------------------------

     * Récupère la liste des articles dans un thread secondaire
     */
    private fun getArticles() {
        lifecycleScope.launch(Dispatchers.IO) {
            val articles = ArticleRepository.getInstance().getArticles()
        }
    }

    /**
     * Rempli le recyclerview avec les données récupérées dans le web service
     * Cette action doit s'effectuer sur le thread principale
     * Car on ne peut mas modifier les éléments de vue dans un thread secondaire
     */
    private fun bindData(articles: List<Article>) {
        lifecycleScope.launch(Dispatchers.Main) {
            //créer l'adapter
            //associer l'adapteur au recyclerview
        }
    }



}