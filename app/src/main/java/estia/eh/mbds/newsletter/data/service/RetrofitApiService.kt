package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {
    //GET --> On lance une requête de type GET
    // everything est l'action du web service qu'on veut apeler
    // Elle sera concaténée avec l'url prédéfini dans retrofit
    @GET("/everything")
    fun list(): Call<List<Article>>

    @GET("/v2/top-headlines")
    fun listTopHeadline(
        @Query("country") country: String): Call<List<Article>>




}