package vn.silverbot99.farm_traders.app.presentation

import android.app.Activity
import vn.silverbot99.farm_traders.func.gasoline_store_detail.GasolineStoreDetailActivity
import vn.silverbot99.farm_traders.func.gasoline_store_detail.GasolineStoreDetailActivityIntent
import vn.silverbot99.farm_traders.func.medical_detail.MedicalDetailActivity
import vn.silverbot99.farm_traders.func.medical_detail.MedicalDetailActivityIntent


class ActivityIntentInjector {
    fun injectIntent(activity: Activity) {
        when (activity) {
            is GasolineStoreDetailActivity -> GasolineStoreDetailActivityIntent.inject(activity)
            is MedicalDetailActivity -> MedicalDetailActivityIntent.inject(activity)
        }
    }
}