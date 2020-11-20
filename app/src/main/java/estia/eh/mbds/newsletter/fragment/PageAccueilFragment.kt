package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Constants


class PageAccueilFragment: Fragment() {
    private lateinit var clickButton: Button
    private lateinit var spinnerCategory: Spinner
    private lateinit var spinnerCountry: Spinner
    private var selectedCountryPos:Int =0
    private var selectedCategoriePos:Int = 0
    private var selectedCountry:String = "All"

    public val COUNTRIES : Array<String> = arrayOf<String>("ae", "ar", "at", "au", "be", "bg", "br", "ca", "ch", "cn", "co", "cu", "cz", "de", "eg", "fr", "gb", "gr", "hk", "hu", "id", "ie", "il", "in", "it", "jp", "kr", "lt", "lv", "ma", "mx", "my", "ng", "nl", "no", "nz", "ph", "pl", "pt", "ro", "rs", "ru", "sa", "se", "sg", "si", "sk", "th", "tr", "tw", "ua", "us", "ve", "za")

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.page_accueil_fragment, container, false)
        clickButton = view.findViewById(R.id.btn_click_me)
        spinnerCategory = view.findViewById(R.id.spinner_category)
        spinnerCountry = view.findViewById(R.id.spinner_country)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.toolbar_name_page_accueil)
        }

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


        clickButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                //getArticle
            }
        })

        return view
    }
}