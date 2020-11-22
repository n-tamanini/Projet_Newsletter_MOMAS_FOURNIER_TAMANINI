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
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R

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
        searchButton = view.findViewById(R.id.find_articles_btn)
        spinnerCategories = view.findViewById(R.id.spinner_category)
        spinnerCountries = view.findViewById(R.id.spinner_country)
        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.toolbar_name_page_accueil)
        }

        val countriesPretty = resources.getStringArray(R.array.country_arrays_pretty)
        val categoriesPretty = resources.getStringArray(R.array.category_arrays_pretty)

        val countries = resources.getStringArray(R.array.country_arrays)
        val categories = resources.getStringArray(R.array.category_arrays)

        var currentCategory = categories[0]
        var currentCountry = countries[0]

        // gestion des spinners et du bouton

        spinnerCategories = view.findViewById(R.id.spinner_category)
        if (spinnerCategories != null) {
            val adapterCategories = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    categoriesPretty
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
                    countriesPretty
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
        searchButton.setOnClickListener {
            val listArticlesFragment = ListArticlesFragment()
            val bundle = Bundle()
            bundle.putString("country", currentCountry)
            bundle.putString("category", currentCategory)
            listArticlesFragment.arguments = bundle
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragment_container, listArticlesFragment)
                addToBackStack(null)
            }?.commit()
        }

        return view
    }
}


