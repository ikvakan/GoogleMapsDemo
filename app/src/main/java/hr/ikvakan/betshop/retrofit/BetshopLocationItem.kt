package hr.ikvakan.betshop.retrofit

import com.google.gson.annotations.SerializedName

data class BetshopLocationItem(
    @SerializedName("count") val count : Int,
    @SerializedName("betshops") val betshops : List<BetshopsItem>
){
    data class BetshopsItem (
        @SerializedName("name") val name : String,
        @SerializedName("location") val location : LocationItem,
        @SerializedName("id") val id : Int,
        @SerializedName("county") val county : String,
        @SerializedName("city_id") val city_id : Int,
        @SerializedName("city") val city : String,
        @SerializedName("address") val address : String
    ){
        data class LocationItem (
            @SerializedName("lng") val lng : Double,
            @SerializedName("lat") val lat : Double
        )
    }
}