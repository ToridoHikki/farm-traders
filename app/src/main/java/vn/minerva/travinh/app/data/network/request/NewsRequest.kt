package vn.minerva.travinh.app.data.network.request

import com.google.gson.annotations.SerializedName

class NewsRequest  (
    @SerializedName("page")
    var page: Int
)