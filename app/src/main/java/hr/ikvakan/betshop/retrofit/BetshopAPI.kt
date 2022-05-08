package hr.ikvakan.betshop.retrofit


import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL="https://interview.superology.dev/"
const val COORDINATES="48.16124,11.60912,48.12229,11.52741"

interface BetshopAPI {
   @GET("betshops")
    suspend fun getBetshopLocationItem(@Query("boundingBox") string: String ) : BetshopLocationItem
}