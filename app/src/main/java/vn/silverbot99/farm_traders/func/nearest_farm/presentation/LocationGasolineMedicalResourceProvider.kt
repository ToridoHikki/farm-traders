package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import vn.silverbot99.core.base.domain.provider.AndroidResourceProvider
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.app.config.ConfigUtil

class LocationGasolineMedicalResourceProvider :AndroidResourceProvider(){
    fun getMapViewStyle(): String {
        return "https://images.vietbando.com/Style/vt_vbddefault/${ConfigUtil.passport?.config?.keyMap}"
    }
    fun getImageBuilding():Int{
        return R.drawable.ic_gas_station
    }
}