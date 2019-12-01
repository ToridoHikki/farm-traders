package vn.silverbot99.farm_traders.func.price_rate

import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment
import vn.silverbot99.farm_traders.func.price_rate.presentation.PriceRateView

class PriceRateFragment  : MvpFragment() {

    override fun createAndroidMvpView(): AndroidMvpView {
        return PriceRateView(getMvpActivity(), PriceRateView.ViewCreator(getMvpActivity(), null))
    }
}