package estia.eh.mbds.newsletter.data.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import estia.eh.mbds.newsletter.R

class ArticleFragment {
    private  lateinit var favorisButton : Button
    private  lateinit var titleArticle : EditText
    private  lateinit var apercuArticle : EditText
    private  lateinit var auteurArticle : EditText
    private  lateinit var dateArticle : EditText
    private  lateinit var lienArticle : EditText

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.article_layout, container, false)

        titleArticle = view.findViewById(R.id.titre_article)
        apercuArticle = view.findViewById(R.id.apercu_article)
        auteurArticle = view.findViewById(R.id.auteur_article)
        dateArticle = view.findViewById(R.id.date_article)
        lienArticle = view.findViewById(R.id.lien_article)
        favorisButton = view.findViewById(R.id.btn_favoris)

        favorisButton.setOnClickListener {

        }

        return view
    }
}