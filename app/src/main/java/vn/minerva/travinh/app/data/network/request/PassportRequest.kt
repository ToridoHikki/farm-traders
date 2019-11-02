package vn.minerva.travinh.app.data.network.request

import com.google.gson.annotations.SerializedName

data class PassportRequest(
    @SerializedName("user") var user: String,
    @SerializedName("pass") var pass: String
)