package vn.silverbot99.farm_traders.app.presentation

import android.app.Activity

import vn.silverbot99.farm_traders.func.verification_phone.VerificationActivity
import vn.silverbot99.farm_traders.func.verification_phone.VerificationActivityIntent


class ActivityIntentInjector {
    fun injectIntent(activity: Activity) {
        when (activity) {
            is VerificationActivity -> VerificationActivityIntent.inject(activity)

        }
    }
}