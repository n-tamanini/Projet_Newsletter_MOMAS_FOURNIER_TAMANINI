package estia.eh.mbds.newsletter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import estia.eh.mbds.newsletter.fragment.ArticleFragment
import androidx.fragment.app.Fragment
import estia.eh.mbds.newsletter.fragment.AboutUsFragment
import estia.eh.mbds.newsletter.fragment.ListArticlesFragment
import estia.eh.mbds.newsletter.fragment.ListFavoritesFragment
import estia.eh.mbds.newsletter.fragment.PageAccueilFragment
import estia.eh.mbds.newsletter.models.Constants


private lateinit var toolbar: Toolbar


class MainActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)


        changeFragment(PageAccueilFragment())
        showFragment(PageAccueilFragment())
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.return_home -> {
                showFragment(PageAccueilFragment())
                true
            }
            R.id.about_us -> {
                showFragment(AboutUsFragment())
                true
            }
            R.id.list_favoris -> {
                showFragment(ListFavoritesFragment())
                true
            }
            R.id.list_article -> {
                showFragment(ListArticlesFragment())
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}