package vn.silverbot99.farm_traders.app.data.network.request

import com.google.gson.annotations.SerializedName

class GasolineStoreRequest(
    @SerializedName("page")
    var page: Int,
    @SerializedName("store_name")
    var storeName: String? = null,
    @SerializedName("Latitude")
    var latitude: Double? = null,
    @SerializedName("Longitude")
    var longitude: Double? = null

)