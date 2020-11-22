package estia.eh.mbds.newsletter

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import estia.eh.mbds.newsletter.fragment.AboutUsFragment
import estia.eh.mbds.newsletter.fragment.ListFavoritesFragment
import estia.eh.mbds.newsletter.fragment.PageAccueilFragment

private lateinit var toolbar: Toolbar


class MainActivity : AppCompatActivity(), NavigationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        showFragment(PageAccueilFragment())
    }

    // Fonction permettant de définir quel est le fragment au démarrage de l'application
    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    // Fonction pour update le titre selon le fragment
    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    // Fonction pour gérer la navigation avec les items du menu_main
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
        }
        return super.onOptionsItemSelected(item)
    }

}
