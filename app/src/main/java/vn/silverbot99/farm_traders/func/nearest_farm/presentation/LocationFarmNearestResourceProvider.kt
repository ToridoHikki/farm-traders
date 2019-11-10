package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil

class LocationFarmNearestResourceProvider :AndroidResourceProvider(){
    fun getMapViewStyle(): String {
        return "https://images.vietbando.com/Style/vt_vbddefault/bbb9c9ad-00b9-4066-bc88-06b401b8eddd"
    }
    fun getImageLocationFarm():Int{
        return R.drawable.ic_building
    }
}