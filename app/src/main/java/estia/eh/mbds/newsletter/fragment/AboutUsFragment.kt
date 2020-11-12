package estia.eh.mbds.newsletter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import estia.eh.mbds.newsletter.NavigationListener
import estia.eh.mbds.newsletter.R
import estia.eh.mbds.newsletter.NavigationListener

class AboutUsFragment : Fragment() {
    private lateinit var listViewMembers: ListView
    private lateinit var listViewLibraries: ListView
    private lateinit var listViewFeatures: ListView




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_us_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.about_us_toolbar_name)
        }

        val memberList: MutableList<String> = ArrayList()
        memberList.add("Lisa MOMAS")
        memberList.add("Alphonse FOURNIER")
        memberList.add("Nicolas TAMANINI")

        val librariesList: MutableList<String> = ArrayList()
        librariesList.add("À compléter")

        val featuresList: MutableList<String> = ArrayList()
        featuresList.add("À compléter")


        listViewMembers = view.findViewById(R.id.list_view_members)
        val membersAdapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_list_item_1,
                memberList
            )
        }
        listViewMembers.adapter = membersAdapter

        listViewLibraries = view.findViewById(R.id.list_view_libraries)
        val librariesAdapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_list_item_1,
                librariesList
            )
        }
        listViewLibraries.adapter = librariesAdapter

        listViewFeatures = view.findViewById(R.id.list_view_features)
        val featuresAdapter = activity?.let {
            ArrayAdapter<String>(
                it,
                android.R.layout.simple_list_item_1,
                featuresList
            )
        }
        listViewFeatures.adapter = featuresAdapter

        return view
    }
    }

