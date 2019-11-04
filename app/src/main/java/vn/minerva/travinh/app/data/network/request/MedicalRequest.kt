package vn.minerva.travinh.app.data.network.request

import com.google.gson.annotations.SerializedName

class MedicalRequest (
    @SerializedName("Latitude")
    var latitude: Double? = null,
    @SerializedName("Longitude")
    var longitude: Double? = null,
    @SerializedName("page")
    var page: Int,
    @SerializedName("medical_name")
    var medicalName: String? = null
    )