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


private lateinit var listViewMembers: ListView

class AboutUsFragment: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.about_us_fragment, container, false)

        (activity as? NavigationListener)?.let {
            it.updateTitle(R.string.about_us_toolbar_name)
        }

        listViewMembers = view.findViewById(R.id.list_view_members)

        val memberList: MutableList<String> = ArrayList()
        memberList.add("Lisa MOMAS")
        memberList.add("Alphonse FOURNIER")
        memberList.add("Nicolas TAMANINI")

        val membersAdapter = activity?.let {
            ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_item,
                    memberList
            )
        }

        membersAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listViewMembers.adapter = membersAdapter




        return view
        }
}