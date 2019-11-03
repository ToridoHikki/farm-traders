package vn.minerva.travinh.app.presentation

import android.app.Activity
import vn.minerva.travinh.func.gasoline_store_detail.GasolineStoreDetailActivity
import vn.minerva.travinh.func.gasoline_store_detail.GasolineStoreDetailActivityIntent
import vn.minerva.travinh.func.medical_detail.MedicalDetailActivity
import vn.minerva.travinh.func.medical_detail.MedicalDetailActivityIntent


class ActivityIntentInjector {
    fun injectIntent(activity: Activity) {
        when (activity) {
            is GasolineStoreDetailActivity -> GasolineStoreDetailActivityIntent.inject(activity)
            is MedicalDetailActivity -> MedicalDetailActivityIntent.inject(activity)
        }
    }
}