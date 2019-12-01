package vn.silverbot99.farm_traders.func.price_rate.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

interface PriceRateContract {

    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

    }
    abstract class Presenter: MvpPresenter<View>() {

    }
}