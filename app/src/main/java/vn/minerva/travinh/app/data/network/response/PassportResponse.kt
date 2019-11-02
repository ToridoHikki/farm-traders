package vn.minerva.travinh.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.minerva.travinh.app.data.network.response.base.BaseResponse

data class PassportResponse(
    @SerializedName("user_id") var userId: Int,
    @SerializedName("key") var key: String,
    @SerializedName("full_name") var fullName: String,
    @SerializedName("email") var email: String,
    @SerializedName("avatar_url") var avatarUrl: String,
    @SerializedName("mobile") var mobile: String,
    @SerializedName("config") var config: Config,
    @SerializedName("user_type_id") var userTypeId: Int,
    @SerializedName("user_type_code") var userTypeCode: String,
    @SerializedName("user_type_name") var userTypeName: String,
    @SerializedName("rule") var rule: String,
    @SerializedName(value = "branch_id", alternate = ["supplier_id", "warehouse_id", "supermarket_id"])
    var ruleId: Int,
    @SerializedName(value = "branch_name", alternate = ["supplier_name", "warehouse_name", "supermarket_name"])
    var ruleName: String
) : BaseResponse() {

    var username = ""
    var password = ""

    data class Config(
        @SerializedName("KEY_MAP") var keyMap: String,
        @SerializedName("BACKGROUND_URL") var backgroundUrl: String
    )


}