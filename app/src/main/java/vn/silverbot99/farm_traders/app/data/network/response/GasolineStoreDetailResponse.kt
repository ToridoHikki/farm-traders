package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

class GasolineStoreDetailResponse (
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
    var storeLat: Double,
    @SerializedName("store_owner")
    var storeOwner: String,
    @SerializedName("store_moblie")
    var storeMobile: String,
    @SerializedName("image_list")
    var imageList: List<ImageList>,
    @SerializedName("next_accreditation_date")
    var nextAccreditationDate: String,
    @SerializedName("tank_list")
    var tankList: List<String>,
    @SerializedName("document_list")
    var documentList: List<String>
) : BaseResponse() {

    data class ImageList(
        @SerializedName("image_type")
        var imageType: String,
        @SerializedName("image_url")
        var imageUrl: String
    )
}