package vn.minerva.travinh.func.medical_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class MedicalDetailViewModel(
    var medicalId: Int,
    var medicalType: String,
    var medicalName: String,
    var medicalOwner: String,
    var medicalMoblie: String,
    var medicalAddress: String,
    var medicalLap: Double,
    var medicalLon: Double,
    var medicalDistrictId: Int,
    var medicalDistrictName: String,
    var medicalWardId: Int,
    var medicalWardName: String,
    var medicalImageList: List<MedicalDetailImageListViewModel>,
    var nextAccreditationDate: String,
    var medicalQualification: String,
    var businessList: List<String>,
    var equipmentList: List<MedicalDetailEquipmentListViewModel>
):ViewModel