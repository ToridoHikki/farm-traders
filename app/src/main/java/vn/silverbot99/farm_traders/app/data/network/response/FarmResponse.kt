package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

class FarmResponse(
    @SerializedName("farms")
    var farm: Farm
): BaseResponse() {
    data class Farm(
        @SerializedName("name")
        var farmName: String,
        @SerializedName("photo")
        var photo: String,
        @SerializedName("lat")
        var lat: Double,
        @SerializedName("long")
        var long: Double,
        @SerializedName("farm_id")
        var farmId: String
    )
}