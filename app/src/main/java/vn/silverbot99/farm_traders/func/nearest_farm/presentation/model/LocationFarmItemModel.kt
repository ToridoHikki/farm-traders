package vn.silverbot99.farm_traders.func.nearest_farm.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import java.io.Serializable

class LocationFarmItemModel(
    var name: String = "",
    var farmId: String = "",
    var lat: Double = 0.0,
    var long: Double = 0.0,
    var photo: String = ""
): ViewModel, Serializable