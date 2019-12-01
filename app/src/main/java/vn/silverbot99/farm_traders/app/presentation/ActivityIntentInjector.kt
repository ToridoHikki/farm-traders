package vn.silverbot99.farm_traders.app.presentation

import android.app.Activity
import vn.silverbot99.farm_traders.func.farm_detail.FarmDetailActivity
import vn.silverbot99.farm_traders.func.farm_detail.FarmDetailActivityIntent
import vn.silverbot99.farm_traders.func.map.MapActivity
import vn.silverbot99.farm_traders.func.map.MapActivityIntent
import vn.silverbot99.farm_traders.func.product_detail.ProductDetailActivity
import vn.silverbot99.farm_traders.func.product_detail.ProductDetailActivityIntent
import vn.silverbot99.farm_traders.func.product_list.ProductListActivity
import vn.silverbot99.farm_traders.func.product_list.ProductListActivityIntent

import vn.silverbot99.farm_traders.func.verification_phone.VerificationActivity
import vn.silverbot99.farm_traders.func.verification_phone.VerificationActivityIntent


class ActivityIntentInjector {
    fun injectIntent(activity: Activity) {
        when (activity) {
            is VerificationActivity -> VerificationActivityIntent.inject(activity)
            is ProductListActivity -> ProductListActivityIntent.inject(activity)
            is FarmDetailActivity -> FarmDetailActivityIntent.inject(activity)
            is ProductDetailActivity -> ProductDetailActivityIntent.inject(activity)
            is MapActivity -> MapActivityIntent.inject(activity)
        }
    }
}