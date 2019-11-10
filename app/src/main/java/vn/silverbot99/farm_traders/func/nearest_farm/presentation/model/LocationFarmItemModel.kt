package vn.silverbot99.farm_traders.func.nearest_farm.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class LocationFarmItemModel(
    var farmId: Int,
    var farmOwner: String,
    var farmName: String,
    var farmAddress: String,
    var farmLap: Double,
    var farmLon: Double,
    var farmDistrictId: Int,
    var farmDistrictName: String,
    var farmWardId: Int,
    var farmWardName: String,
    var farm_thumbnail: String,
    var farmMobile: String,
    var nextAccreditationDate: String
): ViewModel