package vn.minerva.travinh.func.medical_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.annotations.SerializedName

class MedicalDetailEquipmentListViewModel(
    var modelName: String,
    var modelCategory: String,
    var modelManufacture: String,
    var modelElectronic: String,
    var modelYear: String,
    var beginUseDate: String,
    var serial: String
):ViewModel