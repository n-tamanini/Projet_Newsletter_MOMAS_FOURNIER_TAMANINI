package estia.eh.mbds.newsletter.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.models.Constants

class PageAccueilFragment : Fragment() {

    private lateinit var spinnerCategories: Spinner
    private lateinit var spinnerCountries: Spinner
    private lateinit var searchButton: Button

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

        val countries = resources.getStringArray(R.array.country_arrays)
        val categories = resources.getStringArray(R.array.category_arrays)

        var currentCategory = categories[0]
        var currentCountry = countries[0]

        spinnerCategories = view.findViewById(R.id.spinner_category)
        if (spinnerCategories != null) {
            val adapterCategories = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    categories
            )
            spinnerCategories.adapter = adapterCategories

            spinnerCategories.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long) {
                    currentCategory = categories[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        spinnerCountries = view.findViewById(R.id.spinner_country)
        if (spinnerCountries != null) {
            val adapterCountries = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    countries
            )
            spinnerCountries.adapter = adapterCountries

            spinnerCountries.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long) {
                    currentCountry = countries[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        searchButton = view.findViewById(R.id.find_articles_btn)
        searchButton.setOnClickListener{
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_container, ListArticlesFragment())
            transaction?.commit()

        }


        return view
    }
}


