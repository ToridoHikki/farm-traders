package vn.silverbot99.farm_traders.app.data.network.request

import com.google.gson.annotations.SerializedName

class ProductListRequest (
    @SerializedName("farmerId")
    var farmerId: String? = null,
    @SerializedName("categoryId")
    var categoryId: String
)