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
private lateinit var clickButton: Button
private lateinit var spinnerCategory: Spinner
private lateinit var spinnerCountry: Spinner

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
        when(item.itemId){
            R.id.return_home->{
                showFragment(PageAccueilFragment())
                true
            }
            R.id.about_us->{
                showFragment(AboutUsFragment())
                true
            }
            R.id.list_favoris->{
                showFragment(ListFavoritesFragment())
                true
            }
            R.id.list_article->{
                showFragment(ListArticlesFragment())
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private var selectedCountryPos:Int =0
    private var selectedCategoriePos:Int = 0
    private var selectedCountry:String = "All"
fun clickBtn() {
    val view = LayoutInflater.from(this).inflate(R.layout.page_accueil_fragment, null)
    clickButton = findViewById(R.id.btn_click_me)
    spinnerCategory = findViewById(R.id.spinner_category)
    spinnerCountry = findViewById(R.id.spinner_country)

    val COUNTRIES = arrayOf<String>(
        "All",
        "ae",
        "ar",
        "at",
        "au",
        "be",
        "bg",
        "br",
        "ca",
        "ch",
        "cn",
        "co",
        "cu",
        "cz",
        "de",
        "eg",
        "fr",
        "gb",
        "gr",
        "hk",
        "hu",
        "id",
        "ie",
        "il",
        "in",
        "it",
        "jp",
        "kr",
        "lt",
        "lv",
        "ma",
        "mx",
        "my",
        "ng",
        "nl",
        "no",
        "nz",
        "ph",
        "pl",
        "pt",
        "ro",
        "rs",
        "ru",
        "sa",
        "se",
        "sg",
        "si",
        "sk",
        "th",
        "tr",
        "tw",
        "ua",
        "us",
        "ve",
        "za"
    )

    val adapterCountry =
        ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, COUNTRIES)
    adapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    spinnerCountry.adapter = adapterCountry

    spinnerCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
          selectedCountry =COUNTRIES[position]
            selectedCountryPos = position
            /*  val selectedItem = parent.getItemAtPosition(position).toString()
            if (selectedItem == "Add new category") {
                // do your stuff*/
            }
        } // to close the onItemSelected

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }

    clickButton.setOnClickListener(object : View.OnClickListener{
        override fun onClick(v: View?) {
            loadSources()
        }


       /* val intent = Intent(this, ListArticlesFragment::class.java).apply {
        }
        startActivity(intent)*/
    }

}
}