package vn.minerva.travinh.func.location_gasoline_medical.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class LocationMedicalItemModel(
    var medicalId: Int,
    var medicalType: String,
    var medicalOwner: String,
    var medicalName: String,
    var medicalAddress: String,
    var medicalLap: Double,
    var medicalLon: Double,
    var medicalDistrictId: Int,
    var medicalDistrictName: String,
    var medicalWardId: Int,
    var medicalWardName: String,
    var medical_thumbnail: String,
    var medicalMobile: String,
    var nextAccreditationDate: String
): ViewModel