package vn.minerva.travinh.app.data.network.response

import com.google.gson.annotations.SerializedName
import vn.minerva.travinh.app.data.network.response.base.BaseResponse

class MedicalDetailResponse  (
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
    @SerializedName("image_list")
    var medicalImageList: List<ImageMedical>,
    @SerializedName("next_accreditation_date")
    var nextAccreditationDate: String,
    @SerializedName("medical_qualification")
    var medicalQualification: String,
    @SerializedName("business_list")
    var businessList: List<String>,
    @SerializedName("equipment_list")
    var equipmentList: List<Equipment>
): BaseResponse(){
    data class ImageMedical(
        @SerializedName("image_type")
        var imageType: String,
        @SerializedName("image_url")
        var imageUrl: String
    )
    data class Equipment (
        @SerializedName("model_name")
        var modelName: String,
        @SerializedName("model_category")
        var modelCategory: String,
        @SerializedName("model_manufacture")
        var modelManufacture: String,
        @SerializedName("model_electronic")
        var modelElectronic: String,
        @SerializedName("model_year")
        var modelYear: String,
        @SerializedName("begin_use_date")
        var beginUseDate: String,
        @SerializedName("serial")
        var serial: String
    )


}




