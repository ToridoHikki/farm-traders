package vn.minerva.travinh.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.minerva.travinh.app.data.network.response.base.BaseResponse
import java.util.*

class MedicalResponse(
    @SerializedName("medical_list")
    var medicalList: List<Medical>
): BaseResponse(){

    data class Medical (
        @SerializedName("medical_id")
        var medicalId: Int,
        @SerializedName("medical_type")
        var medicalType: String,
        @SerializedName("medical_name")
        var medicalName: String,
        @SerializedName("medical_owner")
        var medicalOwner: String,
        @SerializedName("medical_moblie")
        var medicalMoblie: String,
        @SerializedName("medical_address")
        var medicalAddress: String,
        @SerializedName("medical_lat")
        var medicalLap: Double,
        @SerializedName("medical_lon")
        var medicalLon: Double,
        @SerializedName("medical_district_id")
        var medicalDistrictId: Int,
        @SerializedName("medical_district_name")
        var medicalDistrictName: String,
        @SerializedName("medical_ward_id")
        var medicalWardId: Int,
        @SerializedName("medical_ward_name")
        var medicalWardName: String,
        @SerializedName("medical_thumbnail")
        var medical_thumbnail: String,
        @SerializedName("next_accreditation_date")
        var NextAccreditationDate: String
        )

}
