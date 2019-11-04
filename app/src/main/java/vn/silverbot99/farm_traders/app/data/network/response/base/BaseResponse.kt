package vn.silverbot99.farm_traders.app.data.network.response.base

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.model.GeoObject
import vn.silverbot99.farm_traders.app.data.model.Region

open class BaseResponse {

    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("detail")
    var detail: String = ""

    @SerializedName("code")
    var code: String = ""

    @SerializedName("total_page")
    var totalPage: Int = 0

    @SerializedName("total_record")
    val total: Int = 0

    @SerializedName("total_container")
    val total_container: Int = 0

    open fun getGeoData(): List<GeoObject> {
        return mutableListOf()
    }

    open fun getRegionData(): List<Region> {
        return mutableListOf()
    }


}