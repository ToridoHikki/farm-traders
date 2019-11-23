package vn.silverbot99.farm_traders.func.nearest_farm.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import vn.silverbot99.core.base.presentation.mvp.base.MvpPresenter
import vn.silverbot99.core.base.presentation.mvp.base.MvpView
import vn.silverbot99.farm_traders.func.nearest_farm.presentation.model.LocationFarmItemModel

interface LocationFarmNearestContract {
    interface View: MvpView {
        fun showLoading()

        fun hideLoading()

        fun showToast(message: String)

        fun showError(message: String)

        fun loadData()

        fun showDetailInfo(data: List<ViewModel>)
        //fun showGasolineStoreDatail(data: List<ViewModel>)

        fun getOnMapReadyCallback(): OnMapReadyCallback



        fun getMapView(): MapView
    }
    abstract class Presenter: MvpPresenter<View>() {
        abstract fun getFarmList()
        abstract fun gotoFarmDetail(farm: LocationFarmItemModel)
    }
}