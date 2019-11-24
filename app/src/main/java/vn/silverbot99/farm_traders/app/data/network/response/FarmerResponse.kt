package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

class FarmerResponse (
    @SerializedName("farmer")
    var farmer: Farmer
    ):BaseResponse(){
    data class Farmer(
        @SerializedName("name")
        var farmerName: String,
        @SerializedName("is_male")
        var isMale: Boolean,
        @SerializedName("phone_number")
        var phoneNumber: String,
        @SerializedName("birth_year")
        var birthYear: String,
        @SerializedName("farmer_id")
        var farmerId: String
    )
}