package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

class LocationFarmNearestResponse (
    @SerializedName("farms")
    var farms :List<Farm>
):BaseResponse(){
    data class Farm (
        @SerializedName("name")
        var name: String = "",
        @SerializedName("farm_id")
        var farmId: String = "",
        @SerializedName("lat")
        var lat: Double,
        @SerializedName("long")
        var long: Double,
        @SerializedName("photo")
        var photo: String = ""
        )
}
   /* @SerializedName("page")
    var page: Int,
    @SerializedName("store_list")
    var storeList :List<StoreList>
) :BaseResponse(){

    data class StoreList (
        @SerializedName("company_id")
        var companyId: Int,
        @SerializedName("company_type")
        var companyType: String,
        @SerializedName("company_name")
        var companyName: String,
        @SerializedName("company_owner")
        var companyOwner: String,
        @SerializedName("company_mobile")
        var companyMobile: String,
        @SerializedName("company_address")
        var companyAddress: String,

        @SerializedName("store_id")
        var storeId: Int,
        @SerializedName("store_name")
        var storeName: String,
        @SerializedName("store_address")
        var storeAddress: String,

        @SerializedName("store_district_id")
        var storeDistrictId: Int,
        @SerializedName("store_district_name")
        var storeDistrictName: String,
        @SerializedName("store_ward_id")
        var storeWardId: Int,
        @SerializedName("store_ward_name")
        var storeWardName: String,


        @SerializedName("store_lon")
        var storeLon: Double,
        @SerializedName("store_lat")
        var storeLap: Double,
        @SerializedName("store_owner")
        var storeOwner: String,
        @SerializedName("store_moblie")
        var storeMobile: String,
        @SerializedName("store_thumbnail")
        var storethumbnail: String,
        @SerializedName("next_accreditation_date")
        var nextAccreditationDate: String
    )
}*/