package vn.silverbot99.farm_traders.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.silverbot99.farm_traders.app.data.network.response.base.BaseResponse

data class CategoriesResponse(
    @SerializedName("categories")
    var categoriesList: List<Category>
): BaseResponse(){
    data class Category (
        @SerializedName("name")
        var name: String = "",
        @SerializedName("photo")
        var photo: String = "",
        @SerializedName("category_id")
        var categoryId: String
    )
}