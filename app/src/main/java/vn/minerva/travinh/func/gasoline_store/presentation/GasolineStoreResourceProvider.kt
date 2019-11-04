package vn.minerva.travinh.func.gasoline_store.presentation

import vn.minerva.core.base.domain.provider.AndroidResourceProvider
import vn.minerva.travinh.R

class GasolineStoreResourceProvider: AndroidResourceProvider() {
    fun getTitleGasStore(): String{
        return resourceManager.getString(R.string.tab_gasoline)
    }
}