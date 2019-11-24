package vn.silverbot99.farm_traders.func.farm_detail.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.gson.annotations.SerializedName

class FarmerItemModel (
    var farmerName: String = "",
    var isMale: Boolean = false,
    var phoneNumber: String = "",
    var birthYear: String = "",
    var farmerId: String= ""
):ViewModel