package hr.ikvakan.betshop.model

import com.google.gson.annotations.SerializedName

data class BetshopLocationModel(
    val count: Int,
    val betshops: List<BetshopsModel>
) {
    data class BetshopsModel(
        val name: String,
        val location: LocationModel,
        val id: Int,
        val county: String,
        val city_id: Int,
        val city: String,
        val address: String
    ) {
        data class LocationModel(

            val lng: Double,
            val lat: Double
        )
    }
}