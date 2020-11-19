package estia.eh.mbds.newsletter.fragment

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
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

       /* val editWeb: WebView = findViewById(R.id.url)
        val intent = Intent.extra.getString(context)
        val url = intent.getStringExtra("url")
        editWeb.loadUrl(url!!)
*/
        return view
    }
}