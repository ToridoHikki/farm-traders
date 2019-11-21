package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

class ProductListResponse (
    @SerializedName("products")
    var productList: List<Product>
): BaseResponse(){
    data class Product (
        @SerializedName("product_id")
        var productId: String = "",
        @SerializedName("name")
        var name: String = "",
        @SerializedName("price")
        var price: String = "",
        @SerializedName("description")
        var description: String = "",
        @SerializedName("photo")
        var photo: String = ""
    )
}