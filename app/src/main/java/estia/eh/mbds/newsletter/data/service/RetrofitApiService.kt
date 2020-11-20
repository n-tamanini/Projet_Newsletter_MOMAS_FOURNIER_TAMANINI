package estia.eh.mbds.newsletter.data.service

import estia.eh.mbds.newsletter.models.ArticleList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApiService {

    // Get articles by country
    @GET("/v2/top-headlines")
    fun getTopHeadlinesByCountry(
            @Query("country") country: String): Call<ArticleList>

    // Get articles by country and category
    @GET("/v2/top-headlines")
    fun getTopHeadlinesByCountryAndCategory(
            @Query("country") country: String,
            @Query("category") category: String
    ): Call<ArticleList>



}