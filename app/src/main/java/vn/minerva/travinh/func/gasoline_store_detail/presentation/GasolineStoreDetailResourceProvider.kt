package vn.minerva.travinh.func.gasoline_store_detail.presentation

import android.graphics.drawable.Drawable
import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R
import vn.minerva.travinh.app.config.ConfigUtil

class GasolineStoreDetailResourceProvider:AndroidResourceProvider() {
    fun getMapViewStyle(): String {
        return "https://images.vietbando.com/Style/vt_vbddefault/${ConfigUtil.passport?.config?.keyMap}"
    }
}