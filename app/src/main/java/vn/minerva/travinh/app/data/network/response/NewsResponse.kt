package vn.minerva.travinh.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.minerva.travinh.app.data.network.response.base.BaseResponse

class NewsResponse (
    @SerializedName("page")
    var page: Int,
    @SerializedName("news_list")
    var newsList: List<News>
):BaseResponse(){
    data class News (
        @SerializedName("category_id")
        var categoryId: Int,
        @SerializedName("category_name")
        var categoryName: String,
        @SerializedName("news_id")
        var newsId: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("desc")
        var desc: String,
        @SerializedName("thumbnail")
        var thumbnail: String,
        @SerializedName("created_at")
        var createdAt: String? = null,
        @SerializedName("detail_url")
        var detailUrl: String
    )
}


