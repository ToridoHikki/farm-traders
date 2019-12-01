package vn.silverbot99.farm_traders.func.nearest_farm

import android.content.Context
import android.view.ViewGroup
import vn.silverbot99.core.base.presentation.mvp.android.AndroidMvpView
import vn.silverbot99.core.base.presentation.mvp.android.MvpActivity
import vn.silverbot99.core.base.presentation.mvp.android.MvpFragment
import vn.silverbot99.farm_traders.R
import vn.silverbot99.farm_traders.func.empty.EmptyView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.LocationFarmNearestView

class LocationFarmFragment : MvpFragment() {

    override fun createAndroidMvpView(): AndroidMvpView {
        return LocationFarmNearestView(getMvpActivity(), LocationFarmNearestView.ViewCreator(getMvpActivity(), null))
    }

}