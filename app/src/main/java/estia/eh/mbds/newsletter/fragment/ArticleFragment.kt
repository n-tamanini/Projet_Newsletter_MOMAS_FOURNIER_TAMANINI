package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R

class ArticleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.article_item, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.article_name)
        }

        return view
    }
}