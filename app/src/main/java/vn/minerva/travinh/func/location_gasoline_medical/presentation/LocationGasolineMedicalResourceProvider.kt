package vn.minerva.travinh.func.location_gasoline_medical.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R
import vn.minerva.travinh.app.config.ConfigUtil

class LocationGasolineMedicalResourceProvider :AndroidResourceProvider(){
    fun getMapViewStyle(): String {
        return "https://images.vietbando.com/Style/vt_vbddefault/${ConfigUtil.passport?.config?.keyMap}"
    }
    fun getImageBuilding():Int{
        return R.drawable.ic_gas_station
    }
}