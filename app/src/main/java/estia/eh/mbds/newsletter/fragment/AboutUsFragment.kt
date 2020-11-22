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
        librariesList.add("com.github.bumptech.glide:glide:4.11.0")
        librariesList.add("com.squareup.retrofit2:retrofit:2.6.0")
        librariesList.add("com.google.code.gson:gson:2.8.5")
        librariesList.add("com.squareup.retrofit2:converter-gson:2.4.0")
        librariesList.add("com.squareup.okhttp3:logging-interceptor:4.2.1")
        librariesList.add("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7")
        librariesList.add("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7")
        librariesList.add("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0")
        librariesList.add("androidx.lifecycle:lifecycle-extensions:2.2.0")
        librariesList.add("androidx.lifecycle:lifecycle-common-java8:2.2.0")
        librariesList.add("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
        librariesList.add("androidx.room:room-runtime:2.2.5")
        librariesList.add("androidx.room:room-ktx:2.2.5")

        val featuresList: MutableList<String> = ArrayList()
        featuresList.add("Afficher la page d'accueil")
        featuresList.add("Filtrer le type d'articles que l'on souhaite lire (Pays, catégories)")
        featuresList.add("Afficher la liste des articles")
        featuresList.add("Agrandir un article pour afficher les détails")
        featuresList.add("Ajouter un article dans la liste des articles favoris")
        featuresList.add("Supprimer un article de la liste des articles favoris")
        featuresList.add("Afficher la liste des articles favoris")
        featuresList.add("Afficher la page 'A propos de nous'")



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

